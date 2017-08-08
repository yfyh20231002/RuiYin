package com.example.shichang393.ruiyin.widget.adapter.live;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shichang393.ruiyin.Bean.CurriculumBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.utils.ConstanceValue;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/7/21.
 */

public class CurriculumAdapter extends RecyclerView.Adapter<CurriculumAdapter.ViewHolder> {
    List<CurriculumBean.PaibanBean> list;
    List<CurriculumBean.YhtxBean> yhtx;
    int selectid;
    Context mContext;

    public CurriculumAdapter(List<CurriculumBean.PaibanBean> list, List<CurriculumBean.YhtxBean> yhtx) {
        this.list = list;
        this.yhtx = yhtx;
    }

    public void setSelectid(int a){
        this.selectid=a;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.curriculumitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CurriculumBean.PaibanBean paibanBean = list.get(position);
        holder.time.setText(paibanBean.getBancishijian());
        switch (selectid){
            case 0:
                String xingqiyizhibanrenid = paibanBean.getXingqiyizhibanrenid();
                for (int i = 0; i < yhtx.size(); i++) {
                    if (xingqiyizhibanrenid.equals(yhtx.get(i).getYhid())){
                        Glide.with(mContext).load(ConstanceValue.baseImage+ yhtx.get(i).getYhtx()).into(holder.touxiang);
                        holder.name.setText(yhtx.get(i).getYhxm());
                    }
                }
                break;
            case 1:
                String xingqierzhibanrenid = paibanBean.getXingqierzhibanrenid();
                for (int i = 0; i < yhtx.size(); i++) {
                    if (xingqierzhibanrenid.equals(yhtx.get(i).getYhid())){
                        Glide.with(mContext).load(ConstanceValue.baseImage+ yhtx.get(i).getYhtx()).into(holder.touxiang);
                        holder.name.setText(yhtx.get(i).getYhxm());
                    }
                }
                break;
            case 2:
                String xingqisanzhibanrenid = paibanBean.getXingqisanzhibanrenid();
                for (int i = 0; i < yhtx.size(); i++) {
                    if (xingqisanzhibanrenid.equals(yhtx.get(i).getYhid())){
                        Glide.with(mContext).load(ConstanceValue.baseImage+ yhtx.get(i).getYhtx()).into(holder.touxiang);
                        holder.name.setText(yhtx.get(i).getYhxm());
                    }
                }
                break;
            case 3:
                String xingqisizhibanrenid = paibanBean.getXingqisizhibanrenid();
                for (int i = 0; i < yhtx.size(); i++) {
                    if (xingqisizhibanrenid.equals(yhtx.get(i).getYhid())){
                        Glide.with(mContext).load(ConstanceValue.baseImage+ yhtx.get(i).getYhtx()).into(holder.touxiang);
                        holder.name.setText(yhtx.get(i).getYhxm());
                    }
                }
                break;
            case 4:
                String xingqiwuzhibanrenid = paibanBean.getXingqiwuzhibanrenid();
                for (int i = 0; i < yhtx.size(); i++) {
                    if (xingqiwuzhibanrenid.equals(yhtx.get(i).getYhid())){
                        Glide.with(mContext).load(ConstanceValue.baseImage+ yhtx.get(i).getYhtx()).into(holder.touxiang);
                        holder.name.setText(yhtx.get(i).getYhxm());
                    }
                }
                break;
            default:

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.touxiang)
        ImageView touxiang;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.time)
        TextView time;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
