package com.kkapp.hook;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class HookHandler implements InvocationHandler {

    private static final String TAG = "HookHandler";

    private final Object mBase;
    public HookHandler(Object base) {
        mBase = base;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HLog.d(TAG, "Hook method:" + method.getName() + " called with args:" + Arrays.toString(args));
        return method.invoke(mBase, args);
    }
}
