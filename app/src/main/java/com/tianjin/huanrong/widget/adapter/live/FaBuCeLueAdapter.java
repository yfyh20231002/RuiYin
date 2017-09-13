package com.tianjin.huanrong.widget.adapter.live;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.tianjin.huanrong.R;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/8/15.
 */

public class FaBuCeLueAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private String[] titles = {"操作建议", "跟踪建议"};
    private Context mContext;
    private Drawable drawable = null;
    private String title = null;
    public FaBuCeLueAdapter(FragmentManager fm, List<Fragment> fragments, Context mContext) {
        super(fm);
        this.fragments = fragments;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                title = titles[0];
                drawable = ContextCompat.getDrawable(mContext, R.mipmap.live_icon_caozuohuang);
                break;
            case 1:
                title = titles[1];
                drawable = ContextCompat.getDrawable(mContext, R.mipmap.live_icon_genzong);
                break;
        }
        drawable.setBounds(0, 0, 42, 42);
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
        SpannableString spannableString = new SpannableString(title + "     ");
        spannableString.setSpan(imageSpan, 7, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
