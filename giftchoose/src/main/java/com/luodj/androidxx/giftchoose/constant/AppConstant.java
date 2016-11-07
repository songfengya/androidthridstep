package com.luodj.androidxx.giftchoose.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Administrator on 2016/11/4.
 */
public  class  AppConstant {

    /**
     * appConstant App常量数据类，在程序运行期间只会初始化一次，且在MyApplication中已经初始化
     */
    private static  AppConstant appConstant;

    /**
     *  用于盛放常量数据,可以动态在运行期间添加数据
     */
    public static List<String> list = new ArrayList<>();
//    public static Queue<String> queue= new ArrayBlockingQueue<String>(3);
    private AppConstant() {
    }
    public static  AppConstant getInstance(){
        if (appConstant == null) {
            appConstant = new AppConstant();
        }
        return  appConstant;
    }


}
