package com.tianjin.huanrong.widget.adapter.live;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tianjin.huanrong.Bean.ChatPBean;
import com.tianjin.huanrong.R;
import com.tianjin.huanrong.listener.OnLiveRoomChatTalkListener;
import com.tianjin.huanrong.manager.SharedPreferencesMgr;
import com.tianjin.huanrong.utils.CommonUtil;
import com.tianjin.huanrong.utils.ConstanceValue;
import com.tianjin.huanrong.widget.view.AlignTextView;

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
    String userId;
    int permission;
    private OnLiveRoomChatTalkListener onLiveRoomChatTalkListener; // 对他说监听

    public InteractionAdapter(List<ChatPBean.DataBean.ChatBean> list, Context mContext, OnLiveRoomChatTalkListener onLiveRoomChatTalkListener) {
        this.list = list;
        this.mContext = mContext;
        this.onLiveRoomChatTalkListener = onLiveRoomChatTalkListener;
        inflater = LayoutInflater.from(mContext);
        userId = SharedPreferencesMgr.getuserid();
        permission = SharedPreferencesMgr.getZhanghaoleixing();
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
        final ChatPBean.DataBean.ChatBean chatBean = list.get(position);
        String timedate = CommonUtil.timedate(chatBean.getShuohuashijian(), "MM-dd HH:mm");
        String fyonghuid = chatBean.getFyonghuid();
        switch (type) {
            case TYPE1:
//                fyonghuid等于userid则右边显示，左边隐藏
                if (TextUtils.equals(userId, fyonghuid)) {
                    Glide.with(mContext).load(ConstanceValue.baseImage + "/" + chatBean.getFyonghutouxiang()).into(holder.rightImage);
                    holder.rightName.setText("我");
                    holder.rightTime.setText(timedate);
                    String shuohuaneirong = TextUtils.isEmpty(chatBean.getShuohuaneirong()) ? "" : chatBean.getShuohuaneirong();
                    if (CommonUtil.isImg(shuohuaneirong)) {
                        Glide.with(mContext).load(shuohuaneirong).into(holder.rightContentImage);
                        holder.rightContent.setVisibility(View.GONE);
                        holder.rightContentImage.setVisibility(View.VISIBLE);
                    } else {
                        holder.rightContent.setText(shuohuaneirong);
                        holder.rightContent.setVisibility(View.VISIBLE);
                        holder.rightContentImage.setVisibility(View.GONE);
                    }
                    holder.rightLayout.setVisibility(View.VISIBLE);
                    holder.leftLayout.setVisibility(View.GONE);
                } else {
                    Glide.with(mContext).load(ConstanceValue.baseImage + "/" + chatBean.getFyonghutouxiang()).into(holder.leftImage);
                    holder.leftImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onLiveRoomChatTalkListener.onClickTalkTo(chatBean.getMessageid(), chatBean.getFyonghunicheng(), chatBean.getFyonghuid(),chatBean.getFyonghutouxiang(),chatBean.getShuohuaneirong(), 1);
                        }
                    });
                    holder.leftName.setText(chatBean.getFyonghunicheng());
                    holder.leftTime.setText(timedate);
                    String shuohuaneirong = TextUtils.isEmpty(chatBean.getShuohuaneirong()) ? "" : chatBean.getShuohuaneirong();
                    if (CommonUtil.isImg(shuohuaneirong)) {
                        Glide.with(mContext).load(shuohuaneirong).into(holder.leftContentImage);
                        holder.leftContent.setVisibility(View.GONE);
                        holder.leftContentImage.setVisibility(View.VISIBLE);
                    } else {
                        holder.leftContent.setText(shuohuaneirong);
                        holder.leftContent.setVisibility(View.VISIBLE);
                        holder.leftContentImage.setVisibility(View.GONE);
                    }
                    holder.rightLayout.setVisibility(View.GONE);
                    holder.leftLayout.setVisibility(View.VISIBLE);
                }
                break;
            case TYPE2:
                //               fyonghuid等于userid则右边显示，左边隐藏
                if (TextUtils.equals(userId, fyonghuid)) {
                    //                分析师的头像
                    Glide.with(mContext).load(ConstanceValue.baseImage + "/" + chatBean.getFyonghutouxiang()).into(holder2.mineTouxing);
//                分析师姓名
                    holder2.mineName.setText(chatBean.getFyonghunicheng());
//                分析师回复时间
                    holder2.mineTime.setText(timedate);
//                分析师回复内容
                    holder2.mineContent.setText(chatBean.getShuohuaneirong());
//                用户姓名
                    holder2.mineName2.setText(chatBean.getTyonghunicheng());
//                用户提问内容
                    String relation = chatBean.getRelation();
                    if (relation != null) {
                        holder2.mineContent2.setText(CommonUtil.removeHtmlTag(relation));
                    }
//                用户头像
                    Glide.with(mContext).load(ConstanceValue.baseImage + "/" + chatBean.getTyonghutouxiang()).into(holder2.mineTouxiang);
                    holder2.mineLayout.setVisibility(View.VISIBLE);
                    holder2.otherLayout.setVisibility(View.GONE);
                }else {
                    //                分析师的头像
                    Glide.with(mContext).load(ConstanceValue.baseImage + "/" + chatBean.getFyonghutouxiang()).into(holder2.otherTouxing);
//                分析师姓名
                    holder2.otherName.setText(chatBean.getFyonghunicheng());
//                分析师回复时间
                    holder2.otherTime.setText(timedate);
//                分析师回复内容
                    holder2.otherContent.setText(chatBean.getShuohuaneirong());
//                用户姓名
                    holder2.otherName2.setText(chatBean.getTyonghunicheng());
//                用户提问内容
                    String relation = chatBean.getRelation();
                    if (relation != null) {
                        holder2.otherContent2.setText(CommonUtil.removeHtmlTag(relation));
                    }
//                用户头像
                    Glide.with(mContext).load(ConstanceValue.baseImage + "/" + chatBean.getTyonghutouxiang()).into(holder2.otherTouxiang);
                    holder2.mineLayout.setVisibility(View.GONE);
                    holder2.otherLayout.setVisibility(View.VISIBLE);
                }

                break;
            default:
                break;
        }
        return convertView;
    }



    static class ViewHolder {
        @InjectView(R.id.right_image)
        CircleImageView rightImage;
        @InjectView(R.id.right_name)
        TextView rightName;
        @InjectView(R.id.right_time)
        TextView rightTime;
        @InjectView(R.id.right_content)
        AlignTextView rightContent;
        @InjectView(R.id.right_content_image)
        ImageView rightContentImage;
        @InjectView(R.id.right_layout)
        LinearLayout rightLayout;
        @InjectView(R.id.left_image)
        CircleImageView leftImage;
        @InjectView(R.id.left_name)
        TextView leftName;
        @InjectView(R.id.left_time)
        TextView leftTime;
        @InjectView(R.id.left_content)
        AlignTextView leftContent;
        @InjectView(R.id.left_content_image)
        ImageView leftContentImage;
        @InjectView(R.id.left_layout)
        LinearLayout leftLayout;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    class ViewHolder2 {
        @InjectView(R.id.mine_touxing)
        CircleImageView mineTouxing;
        @InjectView(R.id.mine_name)
        TextView mineName;
        @InjectView(R.id.mine_time)
        TextView mineTime;
        @InjectView(R.id.mine_content)
        AlignTextView mineContent;
        @InjectView(R.id.mine_touxiang)
        CircleImageView mineTouxiang;
        @InjectView(R.id.mine_name2)
        TextView mineName2;
        @InjectView(R.id.mine_content2)
        AlignTextView mineContent2;
        @InjectView(R.id.mine_layout)
        LinearLayout mineLayout;
        @InjectView(R.id.other_touxing)
        CircleImageView otherTouxing;
        @InjectView(R.id.other_name)
        TextView otherName;
        @InjectView(R.id.other_time)
        TextView otherTime;
        @InjectView(R.id.other_content)
        AlignTextView otherContent;
        @InjectView(R.id.other_touxiang)
        CircleImageView otherTouxiang;
        @InjectView(R.id.other_name2)
        TextView otherName2;
        @InjectView(R.id.other_content2)
        AlignTextView otherContent2;
        @InjectView(R.id.other_layout)
        LinearLayout otherLayout;

        ViewHolder2(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
