package u.can.i.up.hook;

import android.util.Log;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;

/**
 * Created by lczgywzyy on 2015/2/4.
 */
public class ClassLoaderHook {
    public static void MyHookStart(){
        MS.hookClassLoad("com.softsec.trick.OriginalActivity",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getMethod("getActivityName");
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                                public Object invoked(Object obj, Object... args) throws Throwable {
                                    Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL!");
                                    return invoke(obj, args);
                                }
                            });
                    }
                });
    }
}
