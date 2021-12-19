package com.kkapp.hook;

import android.app.Activity;
import android.app.Instrumentation;

import java.lang.reflect.Proxy;

public final class HookHelper {

    private static final String TAG = "HookHelper";
    public static void hookActivityManager(Activity activity) {
        try {
            //获取AMN的gDefault单例gDefault，gDefault是静态的
            Object instrumentation = RefInvoke.getFieldObject("android.app.Activity", activity, "mInstrumentation");


            /*// gDefault是一个 android.util.Singleton对象; 我们取出这个单例里面的mInstance字段，IActivityManager类型
            Object rawIActivityManager = RefInvoke.getFieldObject(
                    "android.util.Singleton",
                    gDefault, "mInstance");*/


            //TODO java.lang.ClassCastException: $Proxy0 cannot be cast to android.app.Instrumentation, 要想Hook 非Object 属性，只能静态代理？？？？

            // 创建一个这个对象的代理对象iActivityManagerInterface, 然后替换这个字段, 让我们的代理对象帮忙干活
//            Object proxy = Proxy.newProxyInstance(
//                    Thread.currentThread().getContextClassLoader(),
//                    Instrumentation.class.getInterfaces(),
//                    new HookHandler(instrumentation));

            MyInstrumentation proxy = new MyInstrumentation((Instrumentation) instrumentation);

            //RefInvoke.setFieldObject();
            //把Singleton的mInstance替换为proxy
            RefInvoke.setFieldObject("android.app.Activity", activity, "mInstrumentation", proxy);

        } catch (Exception e) {
            //throw new RuntimeException("Hook Failed", e);
            HLog.e(TAG, "HookInstrumentation error: ", e);
        }
    }

}
