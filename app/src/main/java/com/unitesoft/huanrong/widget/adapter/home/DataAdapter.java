package com.unitesoft.huanrong.widget.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unitesoft.huanrong.Bean.IptMsgBean;
import com.unitesoft.huanrong.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/6/28.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    List<IptMsgBean> list;
    OnItemClickListener listener;

    public DataAdapter(List<IptMsgBean> list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dataitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.dataTitle.setText(list.get(position).getTitle());
        holder.dataContent.setText(list.get(position).getAbstract());
        holder.dataTime.setText(list.get(position).getUpdateDate());
        holder.dataCount.setText(list.get(position).getInfoID()+"");
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

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.data_title)
        TextView dataTitle;
        @InjectView(R.id.data_content)
        TextView dataContent;
        @InjectView(R.id.data_time)
        TextView dataTime;
        @InjectView(R.id.data_count)
        TextView dataCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
