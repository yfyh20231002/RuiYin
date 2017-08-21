package com.example.shichang393.ruiyin.widget.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.widget.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MianZeActivity extends BaseActivity {

    @InjectView(R.id.call)
    ImageView call;
    @InjectView(R.id.infomation)
    ImageView infomation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        setToolBarTitle("免责声明");
        call.setVisibility(View.GONE);
        infomation.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mianze;
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }
}
