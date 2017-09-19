package com.unitesoft.huanrong.widget.activity.mine;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.widget.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AboutusActivity extends BaseActivity {

    @InjectView(R.id.call)
    ImageView call;
    @InjectView(R.id.infomation)
    ImageView infomation;
    @InjectView(R.id.shengming_layout)
    LinearLayout shengmingLayout;
    @InjectView(R.id.tv_banben)
    TextView tvBanben;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        setToolBarTitle("关于我们");
        init();
    }

    private void init() {
        call.setVisibility(View.GONE);
        infomation.setVisibility(View.GONE);

        try {
            PackageManager packageManager=this.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
            tvBanben.setText("V "+packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_aboutus;
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @OnClick(R.id.shengming_layout)
    public void onViewClicked() {
        startActivity(new Intent(AboutusActivity.this, com.unitesoft.huanrong.widget.activity.mine.MianZeActivity.class));
    }
}
