package com.unitesoft.huanrong.widget.activity.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.unitesoft.huanrong.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 开户的弹窗界面
 */
public class KaiHuActivity extends Activity {

    @InjectView(R.id.confirm)
    TextView confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        setContentView(R.layout.activity_kaihu);
        ButterKnife.inject(this);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int wid = metrics.widthPixels;
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (wid * 0.8);
        getWindow().setAttributes(params);
    }

    @OnClick(R.id.confirm)
    public void onViewClicked() {
        com.unitesoft.huanrong.widget.activity.home.KaiHuWebview.startIntent(this);
        finish();
    }
    public static void startIntent(Context context) {
        Intent intent = new Intent(context, KaiHuActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
