package u.can.i.up.hook;

import android.app.PendingIntent;
import android.util.Log;

import com.saurik.substrate.MS;

import java.lang.reflect.Method;

/**
 * Created by lczgywzyy on 2015/2/4.
 */
public class SMSManagerHook {
    public static void SMS_SendTextMessageStart(){
        MS.hookClassLoad("android.telephony.SmsManager", new MS.ClassLoadHook() {
            @Override
            public void classLoaded(Class<?> SmsManager) {
                //code to modify the class when loaded
                Method sendTextMessage;
                try {
                    sendTextMessage = SmsManager.getMethod("sendTextMessage",
                            new Class[]{String.class, String.class, String.class,PendingIntent.class, PendingIntent.class});
                } catch (NoSuchMethodException e) {
                    sendTextMessage = null;
                }

                MS.hookMethod(SmsManager, sendTextMessage, new MS.MethodAlteration() {
                    public Object invoked(Object _this, Object... _args) throws Throwable {
                        Log.i("UCanIUp", "SEND_SMS");
                        Log.i("UCanIUp", "destination:" + _args[0]);
                        Log.i("UCanIUp", "source:" + _args[1]);
                        Log.i("UCanIUp", "text:" + _args[2]);
                        return invoke(_this, _args);
                    }
                });
            }
        });
    }
}
