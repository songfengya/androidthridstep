package com.luodj.androidxx.giftchoose.ui.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.luodj.androidxx.giftchoose.R;
import com.luodj.androidxx.giftchoose.been.First_TitleBeen;
import com.luodj.androidxx.giftchoose.constant.HttpUrl;
import com.luodj.androidxx.giftchoose.presenter.Presenter_FirstFragmengt;
import com.luodj.androidxx.giftchoose.view.ICallBack_Main;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/5.
 */
public class FirstFragment extends Fragment {
    private static final String TAG = "androidxx";
    private Context context ;
//    @BindView(R.id.firstfragment_viewpager) ViewPager viewPager;
//    @BindView(R.id.firstfragment_indicator) LinePageIndicator mIndicator;

    private MyViewPageAdapter myViewPageAdapter;
    private Presenter_FirstFragmengt presenter_firstFragmengt;
   private List<String> titleList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager viewPager;
    private LinePageIndicator mIndicator;

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
//        ButterKnife.bind(context,view);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        presenter_firstFragmengt = new Presenter_FirstFragmengt(new ICallBack_Main() {
            @Override
            public void getMainResult(Object object) {
                First_TitleBeen first_titleBeen = (First_TitleBeen) object;
                First_TitleBeen.DataBean data = first_titleBeen.getData();
                List<First_TitleBeen.DataBean.ChannelsBean> channels = data.getChannels();
                for (int i = 0; i < channels.size(); i++) {
                    String name = channels.get(i).getName();
                    titleList.add(name);
//                     I/androidxx: getMainResult: 精选
//                     I/androidxx: getMainResult: 双11
//                     I/androidxx: getMainResult: 送女票
//                     I/androidxx: getMainResult: 海淘
//                     I/androidxx: getMainResult: 科技范
//                     I/androidxx: getMainResult: 美食
//                    I/androidxx: getMainResult: 送基友
//                     I/androidxx: getMainResult: 送爸妈
//                    I/androidxx: getMainResult: 送同事
//                     I/androidxx: getMainResult: 送宝贝
//                     I/androidxx: getMainResult: 设计感
//                     I/androidxx: getMainResult: 创意生活
//                    I/androidxx: getMainResult: 文艺风
//                     I/androidxx: getMainResult: 奇葩搞怪
//                    I/androidxx: getMainResult: 数码
//                    I/androidxx: getMainResult: 萌萌哒
//                    Log.i(TAG, "getMainResult: "+name);
                }
                initFrament();
                myViewPageAdapter.notifyDataSetChanged();
            }
        }).setPath(HttpUrl.PATH_FIRSTFRAGMENT_TITLE,null,null,null);
        presenter_firstFragmengt.initFirstFragment_Title_Model(context,1);

    }

    private void initFrament() {
        for (int i = 0; i < titleList.size(); i++) {
            fragmentList.add(FirstFragment_Normal.getInstance());
        }
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.firstfragment_viewpager);
        mIndicator = (LinePageIndicator)view.findViewById(R.id.firstfragment_indicator);
        myViewPageAdapter = new MyViewPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(myViewPageAdapter);
        mIndicator.setViewPager(viewPager);
        mIndicator.setSelectedColor(Color.RED);
    }
    class MyViewPageAdapter extends FragmentPagerAdapter{

        public MyViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return titleList.get(position);
        }
    }
}
