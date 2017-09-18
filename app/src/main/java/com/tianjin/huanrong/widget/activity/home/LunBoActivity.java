package com.tianjin.huanrong.widget.activity.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tianjin.huanrong.R;
import com.tianjin.huanrong.widget.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LunBoActivity extends BaseActivity {
    String path;
    @InjectView(R.id.lunbo_image)
    ImageView lunboImage;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        getToolbarTitle().setText("斗金");
        path = getIntent().getStringExtra("picturecontent");
        Glide.with(this)
                .load(path)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setProgress(100);
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(lunboImage);
    }

    public static void startIntent(Context context, String picturecontent) {
        Intent intent = new Intent(context, LunBoActivity.class);
        intent.putExtra("picturecontent", picturecontent);
        context.startActivity(intent);
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lunbo;
    }
}
