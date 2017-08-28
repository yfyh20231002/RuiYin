package com.unitesoft.huanrong.widget.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.widget.activity.BaseActivity;
import com.unitesoft.huanrong.widget.adapter.home.PlayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class PlayCCTVActivity extends BaseActivity {
    List<String> urls = new ArrayList<String>();
    List<String> images = new ArrayList<String>();
    @InjectView(R.id.listview)
    ListView listview;
    PlayAdapter adapter;
    @InjectView(R.id.call)
    ImageView call;
    @InjectView(R.id.infomation)
    ImageView infomation;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("央视");
        ButterKnife.inject(this);
        call.setVisibility(View.GONE);
        infomation.setVisibility(View.GONE);
        initview();
        urls.addAll(getIntent().getStringArrayListExtra("urls"));
        images.addAll(getIntent().getStringArrayListExtra("images"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_playcctv;
    }


    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    private void initview() {
        if (adapter == null) {
            adapter = new PlayAdapter(urls, images, this);
            listview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public static void startIntent(Context context, List<String> urls, List<String> images) {
        Intent intent = new Intent(context, PlayCCTVActivity.class);
        intent.putExtra("urls", (Serializable) urls);
        intent.putExtra("images", (Serializable) images);
        context.startActivity(intent);
    }
}
