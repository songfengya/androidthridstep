package com.luodj.androidxx.giftchoose.presenter;

/**
 * Created by Administrator on 2016/11/5.
 */
public interface ICallBack_Model {
    /**
     * @param result  api解析字符串数据
     * @param type 1 表示tablelayout 2 表示viewpager 3 表示 gridview 4 表示 listview
     */
    void getSuccessResult(String result,int type);
    void getFaillureResult(String result,int type);
}
