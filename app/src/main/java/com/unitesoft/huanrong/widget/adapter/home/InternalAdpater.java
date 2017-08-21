package com.unitesoft.huanrong.widget.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unitesoft.huanrong.Bean.IptMsgBean;
import com.unitesoft.huanrong.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/6/28.
 */

public class InternalAdpater extends RecyclerView.Adapter<InternalAdpater.ViewHolder> {
    List<IptMsgBean> list;
    /**
     * 图片的ID
     */
    int resId;
    OnItemClickListener listener;


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public InternalAdpater(List<IptMsgBean> list,int resId) {
        this.list = list;
        this.resId=resId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iternalitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        IptMsgBean iptMsgBean = list.get(position);
        holder.internalTitle.setText(iptMsgBean.getTitle());
        holder.internalContent.setText(iptMsgBean.getAbstract());
        holder.internalTime.setText(iptMsgBean.getUpdateDate());
        holder.internalCount.setText(iptMsgBean.getInfoID() + "");
        holder.image.setImageResource(resId);
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.image)
        ImageView image;
        @InjectView(R.id.internal_title)
        TextView internalTitle;
        @InjectView(R.id.internal_content)
        TextView internalContent;
        @InjectView(R.id.internal_time)
        TextView internalTime;
        @InjectView(R.id.internal_count)
        TextView internalCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
