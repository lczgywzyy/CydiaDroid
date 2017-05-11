#include <android/log.h>
#include <jni.h>
#include <stdio.h>
#include "substrate.h"
#include "DexFile.h"

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
//TODO odexloadHook
int (* oldodexfileopen)(const void * addr,int len, void ** dvmdex);
int mydvmodexfileopen(const void * addr,int len, void ** dvmdex) {
//    LOGI("call my dvm dex!!:%d", getpid());
    LOGI("call my dvm odex!!");
    { //write to file
        char buf[200];
        sprintf(buf,"/sdcard/odex/odex.%d", getpid());
        //export the dex
        FILE * f=fopen(buf,"wb");
         if(!f) {
            LOGD("error[ODEX] open sdcard file to write");
         }
         else{
             fwrite(addr,1,len,f);
             fclose(f);
         }
    }
    return oldodexfileopen(addr,len,dvmdex);
}
static void ODexloadHook(){
    LOGI("ODexLoad Hook initialized.");
    MSImageRef image;
    //load lib
    image = MSGetImageByName("/system/lib/libdvm.so");
    if (image != NULL) {
        //this is a c++ fuction, use objdump
        void * odexload = MSFindSymbol(image,"_Z21dvmDexFileOpenPartialPKviPP6DvmDex");
        if(odexload == NULL) {
            LOGD("error find _Z21dvmDexFileOpenPartialPKviPP6DvmDex ");
        }
        else{
            LOGI("Succeed find _Z21.....");
            MSHookFunction(odexload,(void*)&mydvmodexfileopen,(void **)&oldodexfileopen);
        }
    }
    else{
        LOGE("ERROR FIND LIBDVM");
    }
}


//TODO dexloadHook
int (* olddexfileopen)(void* pDexFile, const void* data);
int mydvmdexfileopen(void* pDexFile, const void* data) {
//    LOGI("call my dvm dex!!:%d", getpid());
    LOGI("call my dvm dex!!%d", getpid());
    olddexfileopen(pDexFile,data);

    DexFile* pdf = (DexFile*)pDexFile;
    DexHeader* mDexHeader = (DexHeader*)pdf->pHeader;
    pdf->pStringIds;
    { //write to file
        char buf[200] = {'\0'};
        sprintf(buf,"/sdcard/dex/dex.%d", getpid());
//        export the dex
        FILE * f=fopen(buf,"wb");
         if(!f) {
            LOGD("error[DEX] open sdcard file to write");
         }
         else{
//             fwrite(mDexHeader, 1, mDexHeader->headerSize,f);
//             fwrite(pdf->pStringIds, 1, mDexHeader->stringIdsSize * 4, f);
             LOGI("pStringIds:%d", pdf->pStringIds);
             LOGI("stringIdsSize:%d", mDexHeader->stringIdsSize);
             fwrite(pdf->baseAddr, 1, mDexHeader->fileSize, f);
             fclose(f);
         }
    }
    return true;
}
static void DexloadHook(){
    LOGI("DexLoad Hook initialized.");
    MSImageRef image;
    //load lib
    image = MSGetImageByName("/system/lib/libdvm.so");
    if (image != NULL) {
        //this is a c++ fuction, use objdump
        void * dexload = MSFindSymbol(image,"_Z25dexFileSetupBasicPointersP7DexFilePKh");
        if(dexload == NULL) {
            LOGD("error find _Z25dexFileSetupBasicPointersP7DexFilePKh");
        }
        else{
            LOGI("Succeed find _Z25.....");
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
    //_Z21javaLangString_equalsjjjjP6JValue
    ODexloadHook();
    DexloadHook();
}
