package com.luodj.androidxx.giftchoose.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luodj.androidxx.giftchoose.R;

/**
 * Created by Administrator on 2016/11/7.
 */
public class FirstFragment_Normal extends Fragment {
    private  Context context;
    public static FirstFragment_Normal getInstance(){
        return  new FirstFragment_Normal();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firstfragment_normal_item, container, false);
        return view;
    }

}
