package com.tianjin.huanrong.widget.activity.live;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tianjin.huanrong.R;
import com.tianjin.huanrong.manager.SharedPreferencesMgr;
import com.tianjin.huanrong.widget.activity.BaseActivity;
import com.tianjin.huanrong.widget.fragment.dialog.StrategyDialog;
import com.tianjin.huanrong.widget.fragment.live.StudioFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 直播室的详情页
 */
public class StudioActivity extends BaseActivity {

    @InjectView(R.id.call)
    ImageView call;
    @InjectView(R.id.infomation)
    ImageView infomation;
    @InjectView(R.id.tv_strategy)
    TextView tvStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String biaoti = getIntent().getStringExtra("biaoti");
        setToolBarTitle(biaoti);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        call.setVisibility(View.GONE);
        infomation.setVisibility(View.GONE);
       int leixing = SharedPreferencesMgr.getZhanghaoleixing();
        if (isFenxishi(leixing)) {
            tvStrategy.setVisibility(View.VISIBLE);
        } else {
            tvStrategy.setVisibility(View.GONE);
        }
        StudioFragment studioFragment = new StudioFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, studioFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_studio;
    }

    public static void startIntent(Context context,String title) {
        Intent intent = new Intent();
        intent.putExtra("biaoti",title);
        intent.setClass(context, StudioActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.tv_strategy)
    public void onViewClicked() {
        //            发布策略按钮
        StrategyDialog dialog = new StrategyDialog();
        dialog.show(getSupportFragmentManager(), "");
    }


    //判断是否是分析师，是的话就显示发布策略按钮，否则不显示
    private boolean isFenxishi(int permission) {
        if (2 == permission || 3 == permission || 4 == permission || 5 == permission | 6 == permission) {
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
