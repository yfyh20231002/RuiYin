package com.tianjin.huanrong.widget.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tianjin.huanrong.Bean.FlashBean;
import com.tianjin.huanrong.R;
import com.tianjin.huanrong.utils.CommonUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/6/23.
 */

public class FlashAdapter extends RecyclerView.Adapter<FlashAdapter.Holder> {
    List<FlashBean.DataBean.DangtianshujuBean> flist;
    Context mContext;
    OnItemClickLitener listener;


    public FlashAdapter(List<FlashBean.DataBean.DangtianshujuBean> flist, Context mContext) {
        this.flist = flist;
        this.mContext = mContext;
    }

    public void setItemClickLitener(OnItemClickLitener listener) {
        this.listener = listener;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.flashitem, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        ViewGroup.LayoutParams layoutParams1 = holder.view.getLayoutParams();
        if (0==position){
            holder.view.setVisibility(View.VISIBLE);
            layoutParams1.height=CommonUtil.dip2px(13);
        }else {
            holder.view.setVisibility(View.GONE);
            layoutParams1.height=CommonUtil.dip2px(2);
        }
        holder.view.setLayoutParams(layoutParams1);
        long updatetime = flist.get(position).getUpdatetime();
        holder.time.setText(CommonUtil.LongToTime(updatetime));
        ViewGroup.LayoutParams layoutParams = holder.line2.getLayoutParams();
        if ("市场播报".equals(flist.get(position).getClassname())) {
            holder.itemImage.setVisibility(View.GONE);
            holder.itemContent.setText(flist.get(position).getNewscontent());
            layoutParams.height = CommonUtil.dip2px(35);
        } else {
            holder.itemImage.setVisibility(View.VISIBLE);
            holder.itemContent.setText(flist.get(position).getNewscontent());
            String path = flist.get(position).getNewsimageurl();
            if (TextUtils.isEmpty(path)) {
//                layoutParams.height = holder.itemContent.getMeasuredHeight();
                layoutParams.height = CommonUtil.dip2px(35);
            } else {
                Glide.with(mContext)
                        .load(path)
                        .into(holder.itemImage);
//                layoutParams.height = holder.itemContent.getMeasuredHeight() + holder.itemImage.getMeasuredHeight();
                layoutParams.height = CommonUtil.dip2px(160);
            }
            holder.line2.setLayoutParams(layoutParams);
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
        return flist.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @InjectView(R.id.view)
        View view;
        @InjectView(R.id.time)
        TextView time;
        @InjectView(R.id.line2)
        View line2;
        @InjectView(R.id.item_content)
        TextView itemContent;
        @InjectView(R.id.item_image)
        ImageView itemImage;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }
}
