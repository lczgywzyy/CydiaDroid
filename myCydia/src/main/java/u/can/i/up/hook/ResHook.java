package u.can.i.up.hook;

import android.content.res.Resources;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;

/**
 * Created by lczgywzyy on 2015/2/4.
 */
public class ResHook {
    public static void ResHookStart(){
        /**资源
         * */
        MS.hookClassLoad("android.content.res.Resources",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> resources) {
                        Method getColor;
                        try {
                            getColor = resources.getMethod("getColor",
                                    Integer.TYPE);
                        } catch (NoSuchMethodException e) {
                            getColor = null;
                        }

                        if (getColor != null) {
                            MS.hookMethod(resources, getColor, new MS.MethodAlteration<Resources, Integer>() {
                                public Integer invoked(Resources resources, Object... args)
                                        throws Throwable
                                {
                                    return invoke(resources, args) & ~0x0000ff00 | 0x00ff0000;
                                }
                            });
                        }
                    }
                });
    }
}
