package com.unitesoft.huanrong.widget.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.widget.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.rong.imkit.fragment.ConversationFragment;

/**
 * 客服界面
 */
public class ServiceActivity extends BaseActivity {
    @InjectView(R.id.call)
    ImageView call;
    @InjectView(R.id.infomation)
    ImageView infomation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("客服");
        ButterKnife.inject(this);
        call.setVisibility(View.GONE);
        infomation.setImageResource(R.mipmap.service_set_icon);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);
        if (!fragment.onBackPressed()) {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.infomation)
    public void onViewClicked() {
        startActivity(new Intent(ServiceActivity.this,ServiceSetActivity.class));
    }
}
