package com.example.shichang393.ruiyin.widget.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.shichang393.ruiyin.manager.SharedPreferencesMgr;
import com.example.shichang393.ruiyin.utils.ConstanceValue;

/**
 * 启动页
 */
public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //判断是不是第一次启动此应用，第一次启动进入GuideActivity，否则直接进入MainActivity
                if (!SharedPreferencesMgr.getBoolean(ConstanceValue.SP_IS_FIRST_LOGIN, false)) {
                    startActivity(new Intent(LaunchActivity.this,GuideActivity.class));
                }else {
                    startActivity(new Intent(LaunchActivity.this,MainActivity.class));
                }
                finish();
            }
        },2000);
    }
}
