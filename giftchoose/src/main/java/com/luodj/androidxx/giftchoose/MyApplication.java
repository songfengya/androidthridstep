package com.luodj.androidxx.giftchoose;

import android.app.Application;

import com.luodj.androidxx.giftchoose.constant.AppConstant;

/**
 * Created by Administrator on 2016/11/4.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 对AppConstant初始化
        AppConstant instance = AppConstant.getInstance();
    }
}
