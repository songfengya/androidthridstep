package com.luodj.androidxx.giftchoose;

import android.app.Application;

import com.luodj.androidxx.giftchoose.constant.AppConstant;
import com.luodj.androidxx.giftchoose.tool.LogUtils;

/**
 * Created by Administrator on 2016/11/4.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 对AppConstant初始化
        LogUtils.log(MyApplication.class,null);
        AppConstant instance = AppConstant.getInstance();
    }
}
