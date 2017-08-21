package com.unitesoft.huanrong.widget.adapter.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.unitesoft.huanrong.Bean.LiveBean;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.utils.CommonUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/7/12.
 * 首页的热门直播的适配器
 */

public class HomeLiveAdapter extends RecyclerView.Adapter<HomeLiveAdapter.ViewHolder> {
    List<LiveBean.DataBean.LiveRoomsBaseInfoBean> list;
    Context context;
    OnItemClickListener listener;
    int[] images = new int[]{R.mipmap.home_ty, R.mipmap.home_tg};


    public interface OnItemClickListener {
        void onItemClick(View view, int positon);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public HomeLiveAdapter(List<LiveBean.DataBean.LiveRoomsBaseInfoBean> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.homeliveitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.flTy.setBackgroundResource(images[position]);
        int random = (int) (Math.random() * 1501 + 500);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        long currenttiem = System.currentTimeMillis();
        Date date = CommonUtil.LongToDate(currenttiem, "HH:mm");
        String parse1 = "09:00";
        String parse2 = "23:00";
        try {
            Date date1 = dateFormat.parse(parse1);
            Date date2 = dateFormat.parse(parse2);
            if (date.after(date1) && date.before(date2)) {
                holder.count.setText(random + "");
                holder.tyText.setText("直播中");
                holder.tyText.setBackgroundResource(R.mipmap.live_li_bg);
                // 使用代码设置drawableleft
                Drawable drawable = context.getResources().getDrawable(
                        R.mipmap.live_li_play);
                // / 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                holder.tyText.setCompoundDrawables(drawable, null, null, null);
            } else {
                holder.count.setText(0 + "");
                holder.tyText.setText("休息中");
                holder.tyText.setBackgroundResource(R.mipmap.live_xx_bg);
                // 使用代码设置drawableleft
                Drawable drawable = context.getResources().getDrawable(
                        R.mipmap.live_xx_stop);
                // / 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                holder.tyText.setCompoundDrawables(drawable, null, null, null);
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
        @InjectView(R.id.ty_text)
        TextView tyText;
        @InjectView(R.id.fl_ty)
        FrameLayout flTy;
        @InjectView(R.id.count)
        TextView count;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
