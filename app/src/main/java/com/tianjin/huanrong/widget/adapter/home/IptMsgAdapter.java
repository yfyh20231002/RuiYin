package com.tianjin.huanrong.widget.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tianjin.huanrong.Bean.IptMsgBean;
import com.tianjin.huanrong.R;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/27.
 */

public class IptMsgAdapter extends BaseAdapter {
    List<IptMsgBean> list;
    int[] images;
    Context mContext;
    LayoutInflater inflater;

    public IptMsgAdapter(List<IptMsgBean> list, Context context) {
        this.list = list;
        this.mContext = context;
        images = new int[]{R.mipmap.home_eia, R.mipmap.home_sj, R.mipmap.home_fn};
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listviewitem, parent, false);
            holder.imageView= (ImageView) convertView.findViewById(R.id.image);
            holder.title= (TextView) convertView.findViewById(R.id.iptmsg_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String title = list.get(position).getTitle();
        holder.title.setText(title);
        if (title.contains("EIA")){
            holder.imageView.setImageResource(images[0]);
        }else if (title.contains("重要消息")){
            holder.imageView.setImageResource(images[1]);
        }else if (title.contains("狙击非农")){
            holder.imageView.setImageResource(images[2]);
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView title;
    }
}
