package com.example.shichang393.ruiyin.widget.adapter.live;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shichang393.ruiyin.Bean.ChatPBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.utils.CommonUtil;
import com.example.shichang393.ruiyin.utils.ConstanceValue;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mr.zhang on 2017/8/1.
 */

public class InteractionAdapter extends BaseAdapter {
    List<ChatPBean.DataBean.ChatBean> list;
    Context mContext;
    LayoutInflater inflater;
    //为两种布局定义一个标识
    private final int TYPE1 = 0;
    private final int TYPE2 = 1;

    public InteractionAdapter(List<ChatPBean.DataBean.ChatBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        ChatPBean.DataBean.ChatBean chatBean = list.get(position);
        int xiaoxileibie = chatBean.getXiaoxileibie();
        if (0 == xiaoxileibie) {
            return TYPE1;
        } else if (1 == xiaoxileibie) {
            return TYPE2;
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ViewHolder2 holder2 = null;
        int type = getItemViewType(position);
        if (null == convertView) {
            switch (type) {
                case TYPE1:
                    convertView = inflater.inflate(R.layout.chatitem, parent, false);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                    break;
                case TYPE2:
                    convertView = inflater.inflate(R.layout.chatitem2, parent, false);
                    holder2 = new ViewHolder2(convertView);
                    convertView.setTag(holder2);
                    break;
                default:
                    break;
            }

        } else {
            switch (type) {
                case TYPE1:
                    holder = (ViewHolder) convertView.getTag();
                    break;
                case TYPE2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
                default:
                    break;
            }

        }
        ChatPBean.DataBean.ChatBean chatBean = list.get(position);
        String timedate = CommonUtil.timedate(chatBean.getShuohuashijian(), "MM-dd HH:mm");
        switch (type) {
            case TYPE1:
                Glide.with(mContext).load(ConstanceValue.baseImage + "/" + chatBean.getFyonghutouxiang()).into(holder.image);
                holder.name.setText(chatBean.getFyonghunicheng());
                holder.time.setText(timedate);
                String shuohuaneirong = TextUtils.isEmpty(chatBean.getShuohuaneirong())?"":chatBean.getShuohuaneirong();
                if (CommonUtil.isImg(shuohuaneirong)){
                    Glide.with(mContext).load(shuohuaneirong).into(holder.content_image);
                    holder.content.setVisibility(View.GONE);
                    holder.content_image.setVisibility(View.VISIBLE);
                }else {
                    holder.content.setText(shuohuaneirong);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.content_image.setVisibility(View.GONE);
                }
                break;
            case TYPE2:
//                分析师的头像
                Glide.with(mContext).load(ConstanceValue.baseImage + "/" + chatBean.getFyonghutouxiang()).into(holder2.touxing);
//                分析师姓名
                holder2.name.setText(chatBean.getFyonghunicheng());
//                分析师回复时间
                holder2.time.setText(timedate);
//                分析师回复内容
                holder2.content.setText(chatBean.getShuohuaneirong());
//                用户姓名
                holder2.name2.setText(chatBean.getTyonghunicheng());
//                用户提问内容
                String relation = chatBean.getRelation();
                if (relation!=null){
                    holder2.content2.setText(CommonUtil.removeHtmlTag(relation));
                }
//                用户头像
                Glide.with(mContext).load(ConstanceValue.baseImage + "/" + chatBean.getTyonghutouxiang()).into(holder2.touxiang);
                break;
            default:
                break;
        }
        return convertView;
    }


    class ViewHolder {
        @InjectView(R.id.image)
        CircleImageView image;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.time)
        TextView time;
        @InjectView(R.id.content)
        TextView content;
        @InjectView(R.id.content_image)
        ImageView content_image;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    class ViewHolder2 {
        @InjectView(R.id.touxing)
        CircleImageView touxing;
        @InjectView(R.id.name)
        TextView name;
        @InjectView(R.id.time)
        TextView time;
        @InjectView(R.id.content)
        TextView content;
        @InjectView(R.id.touxiang)
        CircleImageView touxiang;
        @InjectView(R.id.name2)
        TextView name2;
//        @InjectView(R.id.time2)
//        TextView time2;
        @InjectView(R.id.content2)
        TextView content2;

        ViewHolder2(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
