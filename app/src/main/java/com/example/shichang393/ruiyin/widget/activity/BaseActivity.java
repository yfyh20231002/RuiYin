package com.example.shichang393.ruiyin.widget.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shichang393.ruiyin.R;

import static com.example.shichang393.ruiyin.R.id.toolbar;


/**
 * Created by Mr.zhang on 2017/6/16.
 */

public abstract class BaseActivity extends AppCompatActivity  {
    private Toolbar mtoolbar;
    private TextView mtitle;
    private ImageView mcall;
    private ImageView minfomation;
    private Context mcontext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mcontext = this;
        mtoolbar = (Toolbar) findViewById(toolbar);
        mtitle = (TextView) findViewById(R.id.title);
        mcall = (ImageView) findViewById(R.id.call);
        minfomation = (ImageView) findViewById(R.id.infomation);
        if (mtoolbar != null) {
            setSupportActionBar(mtoolbar);
        }
        if (mtitle != null) {
            mtitle.setText(getTitle());
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 判断是否有Toolbar,并默认显示返回按钮
         */
        if (null != getToolbar() && isShowBacking()) {
            showBack();
        }
    }

    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    public TextView getToolbarTitle() {
        return mtitle;
    }

    /**
     * 设置头部标题
     *
     * @param title
     */
    public void setToolBarTitle(CharSequence title) {
        if (mtitle != null) {
            mtitle.setText(title);
        } else {
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
    }

    /**
     * this Activity of tool bar.
     * 获取头部.
     *
     * @return support.v7.widget.Toolbar.
     */
    public Toolbar getToolbar() {
        return (Toolbar) findViewById(toolbar);
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolbar().setNavigationIcon(R.mipmap.back);

        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return
     */
    protected boolean isShowBacking() {
        return true;
    }

    /**
     * this activity layout res
     * 设置layout布局,在子类重写该方法.
     *
     * @return res layout xml id
     */
    protected abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("TAG", "onDestroy...");

    }
    protected void intentActivity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(mcontext, tarActivity);
        startActivity(intent);
    }

}
