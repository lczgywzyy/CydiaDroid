package u.can.i.up.cydiadroid;

import u.can.i.up.hook.MyHelloWorldHook;

public class Main {

    public String TAG = "UCanIUp";

    static void initialize() {

//        ResHook.ResHookStart();
//        ExternStorageHook.ExternStorageHookStart();
        MyHelloWorldHook.MyHookStart();
    }
}