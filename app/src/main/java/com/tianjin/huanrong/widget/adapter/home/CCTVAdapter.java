package com.tianjin.huanrong.widget.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tianjin.huanrong.Bean.CCTVBean;
import com.tianjin.huanrong.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public class CCTVAdapter extends RecyclerView.Adapter<CCTVAdapter.ViewHolder> {
    List<CCTVBean.DataBean> list;
    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CCTVAdapter(List<CCTVBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cctvitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CCTVBean.DataBean dataBean = list.get(position);
        holder.cctvTitle.setText(dataBean.getVideoName());
        String createTime = dataBean.getCreateTime();
        holder.cctvTime.setText(createTime.substring(0,11));
        holder.cctvCount.setText(dataBean.getVideoId() + "");
        if (listener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder.itemView,holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.img)
        ImageView img;
        @InjectView(R.id.cctv_title)
        TextView cctvTitle;
        @InjectView(R.id.cctv_time)
        TextView cctvTime;
        @InjectView(R.id.cctv_count)
        TextView cctvCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
