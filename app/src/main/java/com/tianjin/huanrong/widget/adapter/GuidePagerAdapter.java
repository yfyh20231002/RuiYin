package com.tianjin.huanrong.widget.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.tianjin.huanrong.manager.SharedPreferencesMgr;
import com.tianjin.huanrong.utils.ConstanceValue;
import com.tianjin.huanrong.widget.activity.MainActivity;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/12.
 */

public class GuidePagerAdapter extends PagerAdapter {
    List<View> list;
    Activity activity;

    public GuidePagerAdapter(List<View> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position), 0);
        //当滑动到最后一页的时候，监听按钮
        if (position == list.size() - 1) {
            list.get(position).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferencesMgr.setBoolean(ConstanceValue.SP_IS_FIRST_LOGIN,true);
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    activity.finish();
                }
            });
        }
        return list.get(position);
    }
}
