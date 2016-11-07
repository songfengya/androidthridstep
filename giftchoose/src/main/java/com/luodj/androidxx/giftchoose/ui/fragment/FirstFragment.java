package com.luodj.androidxx.giftchoose.ui.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.luodj.androidxx.giftchoose.R;
import com.viewpagerindicator.LinePageIndicator;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/5.
 */
public class FirstFragment extends Fragment {
    private Context context ;
    @BindView(R.id.firstfragment_viewpager) ViewPager viewPager;
    @BindView(R.id.firstfragment_indicator) LinePageIndicator mIndicator;

    public static FirstFragment getInstance(){
         return  new FirstFragment();
     }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firstfragment, container, false);
        ButterKnife.bind(context,view);
        initView(view);
        return view;
    }
    
    private void initView(View view) {

    }

}
