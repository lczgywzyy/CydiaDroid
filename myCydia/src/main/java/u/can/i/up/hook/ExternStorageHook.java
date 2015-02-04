package u.can.i.up.hook;

import android.os.Environment;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;

/**
 * Created by lczgywzyy on 2015/2/4.
 */
public class ExternStorageHook {
    public static void ExternStorageHookStart(){
        /** SD卡
         * */
        MS.hookClassLoad("android.os.Environment",
                new MS.ClassLoadHook() {

                    @Override
                    public void classLoaded(Class<?> arg0) {
                        Method getExternalStorageState;
                        try {
                            getExternalStorageState = arg0.getMethod("getExternalStorageState", new Class<?>[0]);
                        } catch (NoSuchMethodException e) {
                            getExternalStorageState = null;
                        }

                        if (getExternalStorageState != null) {
                            final MS.MethodPointer old = new MS.MethodPointer();

                            MS.hookMethod(arg0, getExternalStorageState, new MS.MethodHook() {
                                @Override
                                public Object invoked(Object obj, Object... args)
                                        throws Throwable {
                                    return Environment.MEDIA_MOUNTED;
                                }
                            }, old);
                        }
                    }
                });
    }
}
