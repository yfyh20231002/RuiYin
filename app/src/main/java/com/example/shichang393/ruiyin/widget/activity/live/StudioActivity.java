package com.example.shichang393.ruiyin.widget.activity.live;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.widget.activity.BaseActivity;
import com.example.shichang393.ruiyin.widget.fragment.live.StudioFragment;

/**
 * 直播室的详情页
 */
public class StudioActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("直播");
        init();
    }

    private void init() {
        StudioFragment studioFragment=new StudioFragment();
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

    public static void startIntent(Context context){
        Intent intent=new Intent(context,StudioActivity.class);
        context.startActivity(intent);
    }

}
