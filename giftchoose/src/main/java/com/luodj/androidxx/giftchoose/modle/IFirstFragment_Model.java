package com.luodj.androidxx.giftchoose.modle;

import android.content.Context;

import com.luodj.androidxx.giftchoose.presenter.ICallBack_Model;

/**
 * Created by Administrator on 2016/11/5.
 */
public interface IFirstFragment_Model {
    /**
     * @param context  上下文
     * @param path    api接口地址
     * @param iCallBack_model 回调结果
     * @param type  解析的种类
     */
    void queryFirstFragmentBeen(Context context, String path,ICallBack_Model iCallBack_model,int type);
}
