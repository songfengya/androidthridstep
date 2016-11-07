package com.luodj.androidxx.giftchoose.ui;

import android.content.ContentResolver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.luodj.androidxx.giftchoose.R;
import com.luodj.androidxx.giftchoose.ui.fragment.FirstFragment;
import com.luodj.androidxx.giftchoose.ui.fragment.FourthFragment;
import com.luodj.androidxx.giftchoose.ui.fragment.SecondFragment;
import com.luodj.androidxx.giftchoose.ui.fragment.ThridFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_frag_layout) FrameLayout frameLayout;
    @BindView(R.id.main_radiogroup) RadioGroup radioGroup;
    @BindView(R.id.main_radiobutton_one) RadioButton mButton_one;
    @BindView(R.id.main_radiobutton_two) RadioButton mButton_two;
    @BindView(R.id.main_radiobutton_three) RadioButton mButton_three;
    @BindView(R.id.main_radiobutton_four) RadioButton mButton_four;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentShowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        setListener();
    }

    private void initView() {
        radioGroup.check(R.id.main_radiobutton_one);
//        mButton_one.setTextColor(getResources().getColor(R.color.color_hong));
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment fragment = FirstFragment.getInstance();
        fragmentTransaction.add(R.id.main_frag_layout,fragment);
         fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
        mCurrentShowFragment =fragment;
    }

    private void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_radiobutton_one:
                        change(FirstFragment.getInstance());
                        break;
                    case R.id.main_radiobutton_two:
                        change(SecondFragment.getInstance());
                        break;
                    case R.id.main_radiobutton_three:
                        change(ThridFragment.getInstance());
                        break;
                    case R.id.main_radiobutton_four:
                        change(FourthFragment.getInstance());
                        break;
                }
            }
        });
    }
        private void  change(Fragment fragment){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if(mCurrentShowFragment != null){
            fragmentTransaction.hide(mCurrentShowFragment);
        }
        if(!fragment.isAdded()){
            fragmentTransaction.add(R.id.main_frag_layout,fragment);
        }else{
            fragmentTransaction.show(fragment);

        }
        fragmentTransaction.commit();
        mCurrentShowFragment =fragment;
    }
}
