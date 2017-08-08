package com.example.shichang393.ruiyin.widget.adapter.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shichang393.ruiyin.Bean.LiveBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.utils.CommonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {
    List<LiveBean.DataBean.LiveRoomsBaseInfoBean> list;
    Context context;
    OnItemClickListener listener;


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public LiveAdapter(List<LiveBean.DataBean.LiveRoomsBaseInfoBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liveitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        LiveBean.DataBean.LiveRoomsBaseInfoBean infoBean = list.get(position);
        holder.liveName.setText(infoBean.getZhiboshimingcheng());
        if (0 == position) {
            holder.tearcherName.setText("李亚蓓  王虎彪");
        } else if (1 == position) {
            holder.tearcherName.setText("幺晓伟");
        }

        holder.liveZhuti.setText(infoBean.getZhiboshijianjie());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        long currenttiem = System.currentTimeMillis();
        Date date = CommonUtil.LongToDate(currenttiem, "HH:mm");
        String parse1 = "09:00";
        String parse2 = "23:00";
        try {
            Date date1 = dateFormat.parse(parse1);
            Date date2 = dateFormat.parse(parse2);
            if (date.after(date1) && date.before(date2)) {
                holder.liveState.setText("直播中");
                holder.liveState.setBackgroundResource(R.mipmap.live_li_bg);
                // 使用代码设置drawableleft
                Drawable drawable = context.getResources().getDrawable(
                        R.mipmap.live_li_play);
                // / 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                holder.liveState.setCompoundDrawables(drawable, null, null, null);
            } else {
                holder.liveState.setText("休息中");
                holder.liveState.setBackgroundResource(R.mipmap.live_xx_bg);
                // 使用代码设置drawableleft
                Drawable drawable = context.getResources().getDrawable(
                        R.mipmap.live_xx_stop);
                // / 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                holder.liveState.setCompoundDrawables(drawable, null, null, null);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


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
        @InjectView(R.id.live_state)
        TextView liveState;
        @InjectView(R.id.live_name)
        TextView liveName;
        @InjectView(R.id.tearcher_name)
        TextView tearcherName;
        @InjectView(R.id.live_zhuti)
        TextView liveZhuti;
        @InjectView(R.id.live_time)
        TextView liveTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int positon);
    }
}
