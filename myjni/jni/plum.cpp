#include <android/log.h>
#include <jni.h>
#include <stdio.h>
#include "substrate.h"

#define LOG_TAG "UCanIUpJni"
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGW(...)  __android_log_print(ANDROID_LOG_WARN, LOG_TAG, __VA_ARGS__)
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

MSConfig(MSFilterExecutable, "/system/bin/app_process")
MSConfig(MSFilterLibrary,"/system/lib/libdvm.so")

//TODO Resource Color
static jint (*_Resources$getColor)(JNIEnv *jni, jobject _this, ...);

static jint $Resources$getColor(JNIEnv *jni, jobject _this, jint rid) {
    jint color = _Resources$getColor(jni, _this, rid);
    LOGI("$Resources$getColor is Called...");
    return color & ~0x0000ff00 | 0x00ff0000;
}

static void OnResources(JNIEnv *jni, jclass resources, void *data) {
    jmethodID method = jni->GetMethodID(resources, "getColor", "(I)I");
    LOGI("OnResources Hook succeed!");
    if (method != NULL)
        MSJavaHookMethod(jni, resources, method,
            &$Resources$getColor, &_Resources$getColor);
}
//TODO mycall
static void (*myRealCall)(JNIEnv *, jobject, ...);
static void newCode(JNIEnv *jni, jobject t) {
    LOGI("!!!!!!!!!!!!!!!");
    (*myRealCall)(jni, t);
}
static void OnMyHelloWorld(JNIEnv *jni, jclass mw, void *data) {
    LOGI("?????????????");
    jmethodID method = jni->GetMethodID(mw, "myRealCall", "()V");
    if (method != NULL)
        MSJavaHookMethod(jni, mw, method, &newCode, &myRealCall);
}
//TODO dexloadHook
int (* olddexfileopen)(const void * addr,int len, void ** dvmdex);
int mydvmdexfileopen(const void * addr,int len, void ** dvmdex) {
//    LOGI("call my dvm dex!!:%d", getpid());
    LOGI("call my dvm dex!!");
    { //write to file
        char buf[200];
        sprintf(buf,"/sdcard/dex.%d",random());
        //export the dex
        FILE * f=fopen(buf,"wb");
         if(!f) {
            LOGD("error open sdcard file to write");
         }
         else{
             fwrite(addr,1,len,f);
             fclose(f);
         }
    }
    return olddexfileopen(addr,len,dvmdex);
}
static void DexloadHook(){
    LOGI("DexLoad Hook initialized.");
    MSImageRef image;
    //load lib
    image = MSGetImageByName("/system/lib/libdvm.so");
    if (image != NULL) {
        //this is a c++ fuction, use objdump
        void * dexload = MSFindSymbol(image,"_Z21dvmDexFileOpenPartialPKviPP6DvmDex");
        if(dexload == NULL) {
            LOGD("error find _Z21dvmDexFileOpenPartialPKviPP6DvmDex ");
        }
        else{
            LOGI("Succeed find _Z21.....");
            MSHookFunction(dexload,(void*)&mydvmdexfileopen,(void **)&olddexfileopen);
        }
    }
    else{
        LOGE("ERROR FIND LIBDVM");
    }
}

//TODO MAIN_INITIAL
MSInitialize {
    //MSJavaHookClassLoad(NULL, "android/content/res/Resources", &OnResources);
    //MSJavaHookClassLoad(NULL, "u/can/i/up/helloworld/MyHelloWorld", &OnMyHelloWorld);
    DexloadHook();
}
