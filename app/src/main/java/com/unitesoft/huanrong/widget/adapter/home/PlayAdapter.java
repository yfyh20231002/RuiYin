package com.unitesoft.huanrong.widget.adapter.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.unitesoft.huanrong.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Mr.zhang on 2017/7/17.
 */

public class PlayAdapter extends BaseAdapter {
    List<String> urls;
    List<String> images;
    Activity activity;
    LayoutInflater inflater;
    ViewHolder holder;
    boolean clicked;

    public PlayAdapter(List<String> urls, List<String> images, Activity activity) {
        this.urls = urls;
        this.images = images;
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.playitem, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.videoPlayer.setUp(urls.get(position), JCVideoPlayer.SCREEN_LAYOUT_LIST,"");
        Glide.with(convertView.getContext()).load(images.get(position)).into(holder.videoPlayer.thumbImageView);
        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.video_player)
        JCVideoPlayerStandard videoPlayer;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
