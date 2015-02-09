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
                            MyCallFunc = myinstance.getMethod("getAndroidSDKVersion");
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
                            MyCallFunc = myinstance.getMethod("prepareDex", new Class[]{Application.class});
//                            MyCallFunc.setAccessible(true);
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
                            MyCallFunc = myinstance.getMethod("invokeStaticMethod");
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
                            MyCallFunc = myinstance.getMethod("invokeMethod");
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
                            MyCallFunc = myinstance.getMethod("getFieldOjbect");
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
                            MyCallFunc = myinstance.getMethod("getStaticFieldOjbect");
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
                            MyCallFunc = myinstance.getMethod("setFieldOjbect");
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
                            MyCallFunc = myinstance.getMethod("setStaticOjbect");
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
    }
}
