package com.example.shichang393.ruiyin.widget.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shichang393.ruiyin.Bean.SuggestionBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.utils.ConstanceValue;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/7/10.
 */

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.ViewHolder> {
    List<SuggestionBean.DataBean.LIVEROOMSSTICKBean> list;
   Context mContext;


    public SuggestionAdapter(List<SuggestionBean.DataBean.LIVEROOMSSTICKBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.suggestionitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SuggestionBean.DataBean.LIVEROOMSSTICKBean liveroomsstickBean = list.get(position);
        Glide.with(mContext).load(ConstanceValue.baseImage+liveroomsstickBean.getFenxishitouxiang()).into(holder.touxing);
        holder.liveName.setText(liveroomsstickBean.getZhiboshimingcheng());
        holder.teacherName.setText(liveroomsstickBean.getFenxishixingming());
        holder.sgtTime.setText(liveroomsstickBean.getZhidingshijian());
        holder.suggestion.setText(liveroomsstickBean.getShuohuaneirong());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.liveName)
        TextView liveName;
        @InjectView(R.id.teacherName)
        TextView teacherName;
        @InjectView(R.id.sgt_time)
        TextView sgtTime;
        @InjectView(R.id.suggestion)
        TextView suggestion;
        @InjectView(R.id.touxing)
        ImageView touxing;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
