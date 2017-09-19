package com.unitesoft.huanrong.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.unitesoft.huanrong.manager.SharedPreferencesMgr;

import io.rong.imkit.RongIM;

/**
 * Created by Mr.zhang on 2017/7/3.
 */

public class RuiYinApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesMgr.init(this,"ruiyin");
        Bugly.init(getApplicationContext(), "8e47c79be6", false);
        RongIM.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
