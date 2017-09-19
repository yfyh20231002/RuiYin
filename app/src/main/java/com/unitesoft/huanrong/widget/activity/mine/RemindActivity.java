package com.unitesoft.huanrong.widget.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.widget.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 我的提醒
 */
public class RemindActivity extends BaseActivity {

    @InjectView(R.id.call)
    ImageView call;
    @InjectView(R.id.infomation)
    ImageView infomation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        setToolBarTitle("我的提醒");
        call.setVisibility(View.GONE);
        infomation.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remind;
    }
}
