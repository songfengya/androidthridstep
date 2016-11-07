package com.luodj.androidxx.giftchoose.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.luodj.androidxx.giftchoose.been.First_GridViewBeen;
import com.luodj.androidxx.giftchoose.been.First_ListViewBeen;
import com.luodj.androidxx.giftchoose.been.First_TitleBeen;
import com.luodj.androidxx.giftchoose.modle.FirstFragment_Model;
import com.luodj.androidxx.giftchoose.modle.IFirstFragment_Model;
import com.luodj.androidxx.giftchoose.view.ICallBack_Main;


/**
 * Created by Administrator on 2016/11/5.
 */
public class Presenter_FirstFragmengt implements ICallBack_Model{
    private IFirstFragment_Model model1 ;
    private String path;
    private ICallBack_Main iCallBack_main;

    public Presenter_FirstFragmengt(ICallBack_Main iCallBack_main) {
        this.iCallBack_main = iCallBack_main;
    }

    public  Presenter_FirstFragmengt setPath(String head, int num, String end, int offset){
        path = head+num+end+offset;
        return  this;
    }

   public void initFirstFragment_Title_Model(Context context,int type){
       model1= new FirstFragment_Model();
       model1.queryFirstFragmentBeen(context,path,this,type);
   }


    /**
     * @param result api解析字符串数据
     * @param type   1 表示tablelayout 2 表示viewpager 3 表示 gridview 4 表示 listview
     */
    @Override
    public void getSuccessResult(String result,int type) {
     switch (type){
         case 1:
             Gson gson = new Gson();
             First_TitleBeen first_titleBeen = gson.fromJson(result, First_TitleBeen.class);
             iCallBack_main.getMainResult(first_titleBeen);
             break;
         case 2:
             Gson gson2 = new Gson();
             break;
         case 3:
             Gson gson3 = new Gson();
             First_GridViewBeen first_gridViewBeen = gson3.fromJson(result, First_GridViewBeen.class);
             iCallBack_main.getMainResult(first_gridViewBeen);
             break;
         case 4:
             Gson gson4 = new Gson();
             First_ListViewBeen first_listViewBeen = gson4.fromJson(result, First_ListViewBeen.class);
             iCallBack_main.getMainResult(first_listViewBeen);
             break;
     }

    }

    @Override
    public void getFaillureResult(String result,int type) {

    }
}
