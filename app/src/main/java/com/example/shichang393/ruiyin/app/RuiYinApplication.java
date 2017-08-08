package com.example.shichang393.ruiyin.app;

import android.app.Application;

import com.example.shichang393.ruiyin.manager.SharedPreferencesMgr;

import io.rong.imkit.RongIM;

/**
 * Created by Mr.zhang on 2017/7/3.
 */

public class RuiYinApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesMgr.init(this,"ruiyin");
        RongIM.init(this);
    }
}
