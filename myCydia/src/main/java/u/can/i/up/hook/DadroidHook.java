package u.can.i.up.hook;

import android.util.Log;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Created by lczgywzyy on 2015/3/31.
 */
public class DadroidHook {
    public static void DadroidHookStart(){
        MS.hookClassLoad("com.ctf.dadroid.MainActivity", new MS.ClassLoadHook() {
            @Override
            public void classLoaded(Class<?> dadroid) {
                //code to modify the class when loaded
                Method result;
                try {
                    result = dadroid.getMethod("result",
                            new Class[]{char[].class, char[].class});
                } catch (NoSuchMethodException e) {
                    result = null;
                }

                MS.hookMethod(dadroid, result, new MS.MethodAlteration() {
                    public Object invoked(Object _this, Object... _args) throws Throwable {
                        Log.i("UCanIUp", "!!!!!!!!!!!!!!!!!!!!");
                        return invoke(_this, _args);
                    }
                });
            }
        });
        MS.hookClassLoad("java.util.Arrays", new MS.ClassLoadHook() {
            @Override
            public void classLoaded(Class<?> dadroid) {
                //code to modify the class when loaded
                Method equals;
                try {
                    equals = dadroid.getMethod("equals",
                            new Class[]{char[].class, char[].class});
                } catch (NoSuchMethodException e) {
                    equals = null;
                }

                MS.hookMethod(dadroid, equals, new MS.MethodAlteration() {
                    public Object invoked(Object _this, Object... _args) throws Throwable {
                        Log.i("UCanIUp", "?????????????????????:" + _args.length);
                        char[] firstParam = (char[])_args[0];
                        char[] secondParam = (char[])_args[1];
                        Log.i("UCanIUp", "First Param's length:" + firstParam.length);
                        Log.i("UCanIUp", "Second Param's length:" + secondParam.length);

                        for (int i = 0; i < secondParam.length; i ++){
                            Log.i("UCanIUp", "" + (int)secondParam[i]);
                        }

//                        String utf16String = new String(secondParam);
//                        byte[] bytes = utf16String.getBytes("UTF-8");
//                        String utf8String = new String(bytes, "UTF-8");
//                        Log.i("UCanIUp", "-----------:" + utf8String);

//                        Charset cs = Charset.forName("UTF-8");
//                        CharBuffer cb = CharBuffer.allocate(secondParam.length);
//                        cb.put (secondParam);
//                        cb.flip();
//                        ByteBuffer bb = cs.encode(cb);
//                        byte[] bBytes = bb.array();
//                        Log.i("UCanIUp", new String(bBytes));

                        return invoke(_this, _args);
                    }
                });
            }
        });

    }
}
