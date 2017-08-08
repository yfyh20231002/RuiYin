package com.example.shichang393.ruiyin.widget.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.widget.adapter.home.PlayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class PlayCCTVActivity extends AppCompatActivity {
    List<String> urls = new ArrayList<String>();
    List<String> images = new ArrayList<String>();
    @InjectView(R.id.listview)
    ListView listview;
    PlayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playcctv);
        ButterKnife.inject(this);
        initview();
        urls.addAll(getIntent().getStringArrayListExtra("urls"));
        images.addAll(getIntent().getStringArrayListExtra("images"));
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
        } else {
            adapter.notifyDataSetChanged();
        }
        listview.setAdapter(adapter);
    }

    public static void startIntent(Context context, List<String> urls, List<String> images) {
        Intent intent = new Intent(context, PlayCCTVActivity.class);
        intent.putExtra("urls", (Serializable) urls);
        intent.putExtra("images", (Serializable) images);
        context.startActivity(intent);
    }
}
