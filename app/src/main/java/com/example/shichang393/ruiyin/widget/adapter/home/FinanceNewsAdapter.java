package com.example.shichang393.ruiyin.widget.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shichang393.ruiyin.Bean.FlashBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.utils.CommonUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/7/5.
 * 快讯标签下的适配器
 */

public class FinanceNewsAdapter extends RecyclerView.Adapter<FinanceNewsAdapter.ViewHolder> {
    List<FlashBean.DataBean.DangtianshujuBean> flist;


    public FinanceNewsAdapter(List<FlashBean.DataBean.DangtianshujuBean> flist) {
        this.flist = flist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_financenews_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date.setText(CommonUtil.timedate(flist.get(position).getUpdatetime()) + "");
        holder.content.setText(CommonUtil.removeHtmlTag(TextUtils.isEmpty(flist.get(position).getNewscontent()) ? flist.get(position).getFinancename() : flist.get(position).getNewscontent()));
        if ("市场播报".equals(flist.get(position).getClassname())) {
            holder.llBottom.setVisibility(View.GONE);
        } else {
            holder.llBottom.setVisibility(View.VISIBLE);
            holder.beforeData.setText("前值：" + flist.get(position).getFinancebefore());
            holder.hopeData.setText("预期：" + flist.get(position).getFinanceprediction());
            holder.actualData.setText(flist.get(position).getFinanceresult());
        }
    }

    @Override
    public int getItemCount() {
        return flist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.date)
        TextView date;
        @InjectView(R.id.content)
        TextView content;
        @InjectView(R.id.beforeData)
        TextView beforeData;
        @InjectView(R.id.actualData)
        TextView actualData;
        @InjectView(R.id.llBottom)
        LinearLayout llBottom;
        @InjectView(R.id.hopeData)
        TextView hopeData;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
