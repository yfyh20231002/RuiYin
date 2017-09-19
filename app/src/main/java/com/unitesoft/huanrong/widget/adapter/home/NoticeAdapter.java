package com.unitesoft.huanrong.widget.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unitesoft.huanrong.Bean.NoticeBean;
import com.unitesoft.huanrong.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/6/26.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.Holder> {
    List<NoticeBean> list;
    Context mContext;
    OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public NoticeAdapter(List<NoticeBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.noticeitem, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.time.setText(list.get(position).getCreateDate());
        holder.count.setText(list.get(position).getInfoID() + "");
        if (listener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    listener.onItemClick(holder.itemView,pos);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @InjectView(R.id.time)
        TextView time;
        @InjectView(R.id.count)
        TextView count;
        @InjectView(R.id.title)
        TextView title;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
