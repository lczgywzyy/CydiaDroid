package u.can.i.up.hook;

import android.content.res.Resources;
import android.util.Log;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;

/**
 * Created by lczgywzyy on 2015/2/4.
 */
public class MyHook {
    public static void MyHookStart(){
        MS.hookClassLoad("u.can.i.up.helloworld.MyHelloWorld",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getMethod("mycall");
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration<Object, Integer>() {
                                public Integer invoked(Object obj, Object... args) throws Throwable {
                                    Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL!");
                                    return invoke(obj, args);
                                }
                            });
                    }
                });
    }
}
