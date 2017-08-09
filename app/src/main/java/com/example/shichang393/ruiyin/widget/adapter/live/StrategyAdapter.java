package com.example.shichang393.ruiyin.widget.adapter.live;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shichang393.ruiyin.Bean.StrategyBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.utils.CommonUtil;
import com.example.shichang393.ruiyin.utils.ConstanceValue;
import com.example.shichang393.ruiyin.widget.view.AlignTextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/7/18.
 */

public class StrategyAdapter extends RecyclerView.Adapter<StrategyAdapter.ViewHolder> {

    List<StrategyBean.DataBean.PROPOSALBean> list;
    Context mContext;


    public StrategyAdapter(List<StrategyBean.DataBean.PROPOSALBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.strategyitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        StrategyBean.DataBean.PROPOSALBean proposalBean = list.get(position);
        Glide.with(mContext).load(ConstanceValue.baseImage+proposalBean.getFyonghutouxiang()).into(holder.touxing);
        holder.name.setText(proposalBean.getFyonghunicheng());
        String timedate = CommonUtil.timedate(proposalBean.getShuohuashijian());
        holder.time.setText(timedate);
        holder.content.setText(proposalBean.getShuohuaneirong());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.touxing)
        ImageView touxing;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.time)
        TextView time;
        @InjectView(R.id.content)
        AlignTextView content;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
