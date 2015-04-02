package u.can.i.up.hook;

import java.lang.reflect.Method;

import com.saurik.substrate.MS;

public class imeihook {

    public static void hookstart() {
//***********************************imei-hook***************************************************************
    	MS.hookClassLoad("android.telephony.TelephonyManager", new MS.ClassLoadHook(){

    		@Override
    		public void classLoaded(Class<?> arg0) {
    			// TODO Auto-generated method stub
    			Method getimei;
    			try {
    				getimei = arg0.getMethod("getDeviceId", null);
    			} catch (NoSuchMethodException e) {
    				// TODO Auto-generated catch block
    				getimei = null;
    				System.out.println("û���ҵ���Ҫhook�ķ���");
    			}
    			if(getimei!=null){
    				final MS.MethodPointer old = new MS.MethodPointer();
    				MS.hookMethod(arg0, getimei, new MS.MethodHook(){

    					@Override
    					public Object invoked(Object arg0, Object... arg1)
    							throws Throwable {
    						// TODO Auto-generated method stub
    						String imei = (String)old.invoke(arg0, arg1);
    		                return imei = "12345IMEI512345";
    					}
    					
    				}, old);
    			}
    		}
    		
    	});
    	
//*******************************************IMSI-hook*****************************************************************************
    	MS.hookClassLoad("android.telephony.TelephonyManager", new MS.ClassLoadHook(){

    		@Override
    		public void classLoaded(Class<?> arg0) {
    			// TODO Auto-generated method stub
    			Method getimsi;
    			try {
    				getimsi = arg0.getMethod("getSubscriberId", null);
    			} catch (NoSuchMethodException e) {
    				// TODO Auto-generated catch block
    				getimsi = null;
    				System.out.println("û���ҵ���Ҫhook�ķ���");
    			}
    			if(getimsi!=null){
    				final MS.MethodPointer old = new MS.MethodPointer();
    				MS.hookMethod(arg0, getimsi, new MS.MethodHook(){

    					@Override
    					public Object invoked(Object arg0, Object... arg1)
    							throws Throwable {
    						// TODO Auto-generated method stub
    						String imsi = (String)old.invoke(arg0, arg1);
    		                return imsi = "12345IMSI512345";
    					}
    					
    				}, old);
    			}
    		}
    		
    	});
    	
    	}

}
