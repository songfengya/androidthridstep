package com.luodj.androidxx.giftchoose.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.luodj.androidxx.giftchoose.R;
import com.luodj.androidxx.giftchoose.been.First_TitleBeen;
import com.luodj.androidxx.giftchoose.constant.HttpUrl;
import com.luodj.androidxx.giftchoose.presenter.Presenter_FirstFragmengt;
import com.luodj.androidxx.giftchoose.tool.LogUtils;
import com.luodj.androidxx.giftchoose.view.ICallBack_Main;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.List;

import static com.luodj.androidxx.giftchoose.R.id.firstfragment_indicator;

/**
 * Created by Administrator on 2016/11/5.
 */
public class FirstFragment_1 extends Fragment {
    private static final String TAG = "androidxx";
    private Context context ;
//    @BindView(R.id.firstfragment_viewpager) ViewPager viewPager;
//    @BindView(R.id.firstfragment_indicator) LinePageIndicator mIndicator;

    private MyViewPageAdapter myViewPageAdapter;
    private Presenter_FirstFragmengt presenter_firstFragmengt;
   private List<String> titleList = new ArrayList<>();
    private List<Integer> idList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager viewPager;
    private TabLayout mIndicator;


    public static FirstFragment_1 getInstance(){
         return  new FirstFragment_1();
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
                    int id = channels.get(i).getId();
                    titleList.add(name);
                    idList.add(id);
//                   LogUtils.log(FirstFragment_1.class,name);
                }
                initFrament();
                myViewPageAdapter.notifyDataSetChanged();
            }
        }).setPath(HttpUrl.PATH_FIRSTFRAGMENT_TITLE,null,null,null);
        presenter_firstFragmengt.initFirstFragment_Title_Model(context,1);

    }

    private void initFrament() {
        for (int i = 0; i < titleList.size(); i++) {
            int id = idList.get(i);
            fragmentList.add(FirstFragment_Normal.getInstance(id));
        }
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.firstfragment_viewpager);
        mIndicator = (TabLayout)view.findViewById(R.id.firstfragment_indicator);
        mIndicator.setTabMode(TabLayout.MODE_SCROLLABLE);
        myViewPageAdapter = new MyViewPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(myViewPageAdapter);
        mIndicator.setupWithViewPager(viewPager);
        mIndicator.setTabTextColors(Color.BLACK,Color.RED);
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
