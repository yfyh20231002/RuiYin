package com.unitesoft.huanrong.widget.adapter.live;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.unitesoft.huanrong.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/8/16.
 */

public class GenzongAdapter extends RecyclerView.Adapter<GenzongAdapter.ViewHolder> {

    private List<String> list;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private int select=0;
    public GenzongAdapter(List<String> list) {
        this.list = list;
    }
    public  void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.genzongitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.btNumber.setText(list.get(position));
        holder.btNumber.setBackgroundColor(Color.WHITE);
        holder.btNumber.setTextColor(Color.parseColor("#333333"));
        if (select==position) {
            holder.btNumber.setBackgroundResource(R.mipmap.live_icon_bg);
            holder.btNumber.setTextColor(Color.WHITE);
        }else {
            holder.btNumber.setBackgroundColor(Color.WHITE);
            holder.btNumber.setTextColor(Color.parseColor("#333333"));
        }
        holder.btNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int item=holder.getLayoutPosition();
                select=item;
                onItemClickListener.onClick(holder.itemView,item);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.bt_number)
        Button btNumber;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
    public interface  OnItemClickListener{
        void onClick(View view,int position);
    }
}
