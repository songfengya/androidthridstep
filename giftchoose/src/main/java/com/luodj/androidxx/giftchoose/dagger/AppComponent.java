package com.luodj.androidxx.giftchoose.dagger;

import com.luodj.androidxx.giftchoose.ui.MainActivity;

import dagger.Component;

/**
 * Created by Administrator on 2016/11/4.
 */
@Component
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
