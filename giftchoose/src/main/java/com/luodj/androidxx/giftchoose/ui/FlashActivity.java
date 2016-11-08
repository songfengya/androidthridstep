package com.luodj.androidxx.giftchoose.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.luodj.androidxx.giftchoose.R;
import com.luodj.androidxx.giftchoose.constant.AppConstant;

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        new MyAsyncTask().execute();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent(FlashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
//    class  MyAsyncTask extends AsyncTask{
//
//        @Override
//        protected Object doInBackground(Object[] params) {
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//            super.onPostExecute(o);
//        }
//    }
}
