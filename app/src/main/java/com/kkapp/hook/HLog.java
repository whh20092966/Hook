package com.kkapp.hook;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class HLog {

    private static final String PRE_TAG = "Hook - ";
    public static void d(String tag, String msg){
        Log.d(PRE_TAG + tag, msg);
    }

    public static void e(String tag, String msg){
        Log.e(PRE_TAG + tag, msg);
    }

    public static void e(String tag, Throwable e){
       e(tag, getStackTrace(e));
    }

    public static void e(String tag, String msg, Throwable e){
        e(tag, msg + getStackTrace(e));
    }

    private static String getStackTrace(Throwable throwable) {

        String stackTrace = "";
        try (StringWriter sw = new StringWriter();
             PrintWriter pw = new PrintWriter(sw)){
            throwable.printStackTrace(pw);
            stackTrace = sw.toString();
        } catch (IOException e) {
            e("KkLog", "get stack trace fail !");
        }

        return stackTrace;
    }
}
