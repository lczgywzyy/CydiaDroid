package u.can.i.up.hook;

import android.util.Log;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;

/**
 * Created by lczgywzyy on 2015/2/4.
 */
public class QQpimHook {
    public static void QQpimHookStart(){
        MS.hookClassLoad("com.tencent.qqpim.sdk.h.a.a",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getMethod("a");
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                                public Object invoked(Object obj, Object... args) throws Throwable {
                                    Log.i("UCanIUp", "QQpimHook Succeeddddddddddddddddddd!");
                                    return invoke(obj, args);
                                }
                            });
                    }
                });
    }
}
