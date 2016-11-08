package com.luodj.androidxx.giftchoose.modle;

import android.content.Context;

import com.luodj.androidxx.giftchoose.been.First_TitleBeen;
import com.luodj.androidxx.giftchoose.presenter.ICallBack_Model;
import com.luodj.androidxx.giftchoose.tool.ICallBack;
import com.luodj.androidxx.giftchoose.tool.LogUtils;
import com.luodj.androidxx.giftchoose.tool.OkHttpTools;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/5.
 */
public class FirstFragment_Model implements IFirstFragment_Model {
//    Call queryFirst_TitleBeen();
////    Call quertFirst_GridViewBeen();
////    Call quertFirst_ListViewBeen();
    public void queryFirstFragmentBeen(Context context, String path, final ICallBack_Model iCallBack_model, final int type){
        OkHttpTools.initOkHttp(path,context).get().setCallBack(new ICallBack() {
            @Override
            public void success(String result) {
                iCallBack_model.getSuccessResult(result,type);
            }
            @Override
            public void failure(String result) {
                 iCallBack_model.getFaillureResult(result,type);
            }
        });
    }
}
