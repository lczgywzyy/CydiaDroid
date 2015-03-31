package u.can.i.up.cydiadroid;

import u.can.i.up.hook.ClassLoaderHook;
import u.can.i.up.hook.DadroidHook;
import u.can.i.up.hook.MyHelloWorldHook;
import u.can.i.up.hook.QQpimHook;

public class Main {

    public String TAG = "UCanIUp";

    static void initialize() {

//        ResHook.ResHookStart();
//        ExternStorageHook.ExternStorageHookStart();
//        MyHelloWorldHook.MyHookStart();
//        QQpimHook.QQpimHookStart();
//        ClassLoaderHook.MyHookStart();
        DadroidHook.DadroidHookStart();
    }
}