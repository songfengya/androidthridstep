package com.luodj.androidxx.giftchoose.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/2.
 */
public class OkHttpTools {
    // 每个工程中建议只有一个OkHttpClient的对象
    private  static  OkHttpClient okHttpClient;
   private static List<Call> callList = new ArrayList<>();
    public static OkHttpHelper initOkHttp(String path, Context context){
        if (!networkIsWork(context)) {
            return null;
        }
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }

        return new OkHttpHelper(path);
    }
    private static  boolean networkIsWork(Context context){
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivity.getActiveNetworkInfo();
        if (activeNetworkInfo == null){
            return  false;
        }
        return activeNetworkInfo.isAvailable();
    }
    public static  void removeAll(){
        for (int i = 0; i < callList.size(); i++) {
            callList.get(i).cancel();
        }
        callList.clear();
    }
    public static  class  OkHttpHelper{
        private  ICallBack iCallBack;
        String path ;
        public OkHttpHelper(String path) {
            this.path = path;
        }
        public void setCallBack(ICallBack iCallBack){
            this.iCallBack = iCallBack;
        }
        private  Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int what = msg.what;
                switch (what){
                    case 0:
                        // 通过接口回调 将值传到Ui主线程中
                        String result  = (String) msg.obj;
                        iCallBack.failure(result);
                        break;
                    case 1:
                        // 通过接口回调 将值传到Ui主线程中
                        String result2 = (String) msg.obj;
                        iCallBack.success(result2);
                        break;
                }
            }
        };
        /**
         *   FormBody formBody = new FormBody.Builder().add("参数","参数的值");
         *   @param formBody   需要根据实际情况来定义；
         */
        public OkHttpHelper post(FormBody formBody){
            Request request = new Request.Builder().url(path).post(formBody).build();
            Call call = okHttpClient.newCall(request);
            callList.add(call);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Message message = handler.obtainMessage();
                    message.what=0;
                    message.obj = e.getMessage();
                    handler.sendMessage(message);
                    callList.remove(call);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Message message = handler.obtainMessage();
                    message.what=1;
                    message.obj = response.body().string();
                    handler.sendMessage(message);
                    response.body().close();
                }
            });
            callList.remove(call);
             return this;
        }
        public  OkHttpHelper get(){
            Request request = new Request.Builder().url(path).get().build();
            Call call = okHttpClient.newCall(request);
            callList.add(call);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Message message = handler.obtainMessage();
                    message.what=0;
                    message.obj = e.getMessage();
                    handler.sendMessage(message);
                    callList.remove(call);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String name = Thread.currentThread().getName();
                    Log.i("androidxx", "onResponse: "+name);
                    Message message = handler.obtainMessage();
                    message.what=1;
                    message.obj = response.body().string();
                    handler.sendMessage(message);
                    response.body().close();
                }
            });
            callList.remove(call);
            return this;

        }
    }
}
