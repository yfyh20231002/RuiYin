package com.example.shichang393.ruiyin.widget.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shichang393.ruiyin.Bean.FlashBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.utils.CommonUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/7/7.
 */

public class RemindAdapter extends RecyclerView.Adapter<RemindAdapter.ViewHolder> {
    List<FlashBean.DataBean.DangtianshujuBean> list;
    OnItemClickLitener listener;
    Context mContext;

    public RemindAdapter(List<FlashBean.DataBean.DangtianshujuBean> list) {
        this.list = list;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setItemClickLitener(OnItemClickLitener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.reminditem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.remindTime.setText(CommonUtil.timedate(list.get(position).getUpdatetime())+"");
        holder.remindContent.setText(list.get(position).getNewscontent());
        String name=list.get(position).getClassname();
        if ("市场播报".equals(name)) {
            holder.remindLable.setText("播报");
            holder.remindImage.setVisibility(View.GONE);
        }else if ("政经要闻".equals(name)){
            holder.remindLable.setText("要闻");
            holder.remindImage.setVisibility(View.VISIBLE);
            String path = list.get(position).getNewsimageurl();
            if (TextUtils.isEmpty(path)) {
                return;
            }
            Glide.with(mContext)
                    .load(path)
                    .into(holder.remindImage);
        }
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    listener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.remind_content)
        TextView remindContent;
        @InjectView(R.id.remind_lable)
        TextView remindLable;
        @InjectView(R.id.remind_time)
        TextView remindTime;
        @InjectView(R.id.remind_image)
        ImageView remindImage;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
