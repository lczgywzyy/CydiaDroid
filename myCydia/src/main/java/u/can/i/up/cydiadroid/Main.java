package u.can.i.up.cydiadroid;

import java.lang.reflect.Method;

import android.app.PendingIntent;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import com.saurik.substrate.MS;

import u.can.i.up.hook.ExternStorageHook;
import u.can.i.up.hook.MyHook;
import u.can.i.up.hook.ResHook;

public class Main {

    public String TAG = "UCanIUp";

    static void initialize() {

//        ResHook.ResHookStart();
//        ExternStorageHook.ExternStorageHookStart();
        MyHook.MyHookStart();
    }
}