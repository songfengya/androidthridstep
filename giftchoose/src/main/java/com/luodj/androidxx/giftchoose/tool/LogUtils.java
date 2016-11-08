package com.luodj.androidxx.giftchoose.tool;

import android.util.Log;

/**
 * Created by Administrator on 2016/11/7.
 */
public class LogUtils {
    private static final String TAG = "androidxx";
    public  static void log(Class clazz, String string){
        Log.i(TAG,clazz.getName()+":"+string);

    }
}
