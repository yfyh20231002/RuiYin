package com.unitesoft.huanrong.markcenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.unitesoft.huanrong.R;

/**
 * Created by Mr.zhang on 2017/9/11.
 */

public class McAdapter extends BaseAdapter {
    private String[] array;
    private Context mContext;
    private LayoutInflater inflater;

    public McAdapter(String[] array, Context mContext) {
        this.array = array;
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return array.length;
    }

    @Override
    public Object getItem(int position) {
        return array[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=inflater.inflate(R.layout.mc_item,null);
            TextView textView= (TextView) convertView.findViewById(R.id.mc_item);
            textView.setText(array[position]);
        }
        return convertView;
    }
}
