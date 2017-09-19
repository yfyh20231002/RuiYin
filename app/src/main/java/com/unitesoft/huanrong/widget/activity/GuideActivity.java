package com.unitesoft.huanrong.widget.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.widget.adapter.GuidePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 引导页
 */
public class GuideActivity extends AppCompatActivity {

    @InjectView(R.id.viewpager_guide)
    ViewPager viewpagerGuide;
    List<View> list = new ArrayList<>();
    int[] images = new int[]{R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3, R.mipmap.guide4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  //设置全屏
        setContentView(R.layout.activity_guide);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(images[i]);
            list.add(imageView);
        }
        GuidePagerAdapter adapter = new GuidePagerAdapter(list, this);
        viewpagerGuide.setAdapter(adapter);
    }

    /**
     * 在引导画面中，禁止回退
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
