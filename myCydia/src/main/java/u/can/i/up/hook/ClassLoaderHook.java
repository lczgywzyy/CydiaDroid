package u.can.i.up.hook;

import android.app.Application;
import android.app.PendingIntent;
import android.util.Log;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;

/**
 * Created by lczgywzyy on 2015/2/4.
 */
public class ClassLoaderHook {
    public static void MyHookStart(){
        MS.hookClassLoad("com.cmcc.omss.AppMgr",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getDeclaredMethod("getAndroidSDKVersion");
                            MyCallFunc.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                            public Object invoked(Object obj, Object... args) throws Throwable {
                                Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL getAndroidSDKVersion!");
                                return invoke(obj, args);
                            }
                        });
                    }
                });
        MS.hookClassLoad("com.cmcc.omss.AppMgr",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getDeclaredMethod("prepareDex", new Class[]{Application.class});
                            MyCallFunc.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                            public Object invoked(Object obj, Object... args) throws Throwable {
                                Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL prepareDex!");
                                return invoke(obj, args);
                            }
                        });
                    }
                });
        MS.hookClassLoad("com.cmcc.omss.RefInvoke",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getDeclaredMethod("invokeStaticMethod", new Class[]{String.class, String.class, Class[].class, Object[].class});
                            MyCallFunc.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                                public Object invoked(Object obj, Object... args) throws Throwable {
                                    Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL invokeStaticMethod!");
                                    return invoke(obj, args);
                                }
                            });
                    }
                });
        MS.hookClassLoad("com.cmcc.omss.RefInvoke",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getDeclaredMethod("invokeMethod", new Class[]{String.class, String.class, Object.class, Class[].class, Object[].class});
                            MyCallFunc.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                            public Object invoked(Object obj, Object... args) throws Throwable {
                                Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL invokeMethod!");
                                return invoke(obj, args);
                            }
                        });
                    }
                });
        MS.hookClassLoad("com.cmcc.omss.RefInvoke",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getDeclaredMethod("getFieldOjbect", new Class[]{String.class, Object.class, String.class});
                            MyCallFunc.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                            public Object invoked(Object obj, Object... args) throws Throwable {
                                Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL getFieldOjbect!");
                                return invoke(obj, args);
                            }
                        });
                    }
                });
        MS.hookClassLoad("com.cmcc.omss.RefInvoke",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getDeclaredMethod("getStaticFieldOjbect", new Class[]{String.class, String.class});
                            MyCallFunc.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                            public Object invoked(Object obj, Object... args) throws Throwable {
                                Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL getStaticFieldOjbect!");
                                return invoke(obj, args);
                            }
                        });
                    }
                });
        MS.hookClassLoad("com.cmcc.omss.RefInvoke",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getDeclaredMethod("setFieldOjbect", new Class[]{String.class, String.class, Object.class, Object.class});
                            MyCallFunc.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                            public Object invoked(Object obj, Object... args) throws Throwable {
                                Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL setFieldOjbect!");
                                return invoke(obj, args);
                            }
                        });
                    }
                });
        MS.hookClassLoad("com.cmcc.omss.RefInvoke",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getDeclaredMethod("setStaticOjbect", new Class[]{String.class, String.class, Object.class});
                            MyCallFunc.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                            public Object invoked(Object obj, Object... args) throws Throwable {
                                Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL setStaticOjbect!");
                                return invoke(obj, args);
                            }
                        });
                    }
                });
        //TODO Error
        MS.hookClassLoad("dalvik.system.DexClassLoader",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
//                            MyCallFunc = myinstance.getDeclaredMethod("openDexFile", new Class[]{Application.class});
                            MyCallFunc = myinstance.getDeclaredMethod("DexClassLoader", new Class[]{String.class, String.class, String.class, ClassLoader.class});
                            MyCallFunc.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                            public Object invoked(Object obj, Object... args) throws Throwable {
                                Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL DexClassLoaderrrrrrrrrrrrrrrrrr!");
                                return invoke(obj, args);
                            }
                        });
                    }
                });
        //TODO Error
        MS.hookClassLoad("dalvik.system.DexFile",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getDeclaredMethod("openDexFile", new Class[]{byte[].class});
                            MyCallFunc.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                            public Object invoked(Object obj, Object... args) throws Throwable {
                                Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL openDexFileeeeeeeeeeeeeeeeeeee!");
                                return invoke(obj, args);
                            }
                        });
                    }
                });
        //Android 4.3非内存加载已经测试成功。
        MS.hookClassLoad("dalvik.system.DexFile",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getDeclaredMethod("openDexFile", new Class[]{String.class, String.class, int.class});
                            MyCallFunc.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                            public Object invoked(Object obj, Object... args) throws Throwable {
                                Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL openDexFile!");
                                return invoke(obj, args);
                            }
                        });
                    }
                });
    }
}
