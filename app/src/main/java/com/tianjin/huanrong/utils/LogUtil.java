package com.tianjin.huanrong.utils;

import android.util.Log;



public class LogUtil {
    public final static int VERBOSE=1;
    public final static int DEBUG=2;
    public final static int INFO=3;
    public final static int WARN=4;
    public final static int ERROR=5;
    public final static int NOTHING=6;
    public final static int level=VERBOSE;

    public static void v(String tag,String msg){
        if (level<=VERBOSE){
            Log.v(tag,msg);
        }
    }
    public static void d(String tag,String msg){
        if (level<=DEBUG){
            Log.d(tag,msg);
        }
    }
    public static void i(String tag,String msg){
        if (level<=INFO){
            Log.i(tag,msg);
        }
    }
    public static void e(String tag,String msg){
        if (level<=ERROR){
            Log.e(tag,msg);
        }
    }
    public static void w(String tag,String msg){
        if (level<=WARN){
            Log.w(tag,msg);
        }
    }
}
