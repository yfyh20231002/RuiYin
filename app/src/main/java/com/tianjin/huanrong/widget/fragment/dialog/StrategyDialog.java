package com.tianjin.huanrong.widget.fragment.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.androidkun.xtablayout.XTabLayout;
import com.tianjin.huanrong.R;
import com.tianjin.huanrong.listener.live.CancleCallback;
import com.tianjin.huanrong.widget.adapter.live.FaBuCeLueAdapter;
import com.tianjin.huanrong.widget.fragment.live.CaozuoFragment;
import com.tianjin.huanrong.widget.fragment.live.GenzongFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Mr.zhang on 2017/8/15.
 * 发布策略的弹窗
 */

public class StrategyDialog extends DialogFragment  implements CancleCallback{

    @InjectView(R.id.tablayout)
    XTabLayout tablayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    private List<Fragment> list=new ArrayList<>();
    private FaBuCeLueAdapter adapter;
    private Context mContext;
    private CaozuoFragment caozuoFragment;
    private GenzongFragment genzongFragment;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int wid = metrics.widthPixels;
        int height=metrics.heightPixels;
        WindowManager.LayoutParams mLayoutParams = getDialog().getWindow().getAttributes();
        mLayoutParams.width =(int) (wid * 0.9);
        mLayoutParams.height = (int) (height * 0.6);
        getDialog().getWindow().setAttributes(mLayoutParams);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 设置背景透明
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // 去掉标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // set cancel on touch outside
        getDialog().setCanceledOnTouchOutside(true);
        View view = inflater.inflate(R.layout.stratedialog, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        caozuoFragment=new CaozuoFragment();
        caozuoFragment.setCancleCallback(this);
        genzongFragment=new GenzongFragment();
        genzongFragment.setCancleCallback(this);
        list.add(caozuoFragment);
        list.add(genzongFragment);
        adapter=new FaBuCeLueAdapter(getChildFragmentManager(),list,mContext);
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(1);
        tablayout.setupWithViewPager(viewpager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void cancle() {
        dismiss();
    }
}
