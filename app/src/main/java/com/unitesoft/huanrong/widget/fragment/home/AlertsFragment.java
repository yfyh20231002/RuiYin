package com.unitesoft.huanrong.widget.fragment.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.widget.adapter.ChanelAdapter;
import com.unitesoft.huanrong.widget.fragment.live.BaseFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 快讯标签下的财经快讯
 */
public class AlertsFragment extends BaseFragment {


    @InjectView(R.id.indicator)
    TabPageIndicator indicator;
    @InjectView(R.id.vpFinanceNews)
    ViewPager vpFinanceNews;

    private List<Fragment> fragments = new ArrayList<>();
    private ChanelAdapter chanelAdapter;
    private String[] titles = new String[]{"经济数据", "市场播报"};
    private  View view;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private Context mContext;
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            Context themeFragment = new ContextThemeWrapper(mContext, R.style.Theme_PageIndicatorFinanceNews);
            LayoutInflater localInflater = inflater.cloneInContext(themeFragment);
            view = localInflater.inflate(R.layout.fragment_alerts, container, false);
            isPrepared = true;
            ButterKnife.inject(this, view);
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    @Override
    protected void lazyLoad() {
        if(!isPrepared || !isVisible) {
            return;
        }
        init();
    }

    private void init() {
        fragments.clear();
        fragments.add(new com.unitesoft.huanrong.widget.fragment.home.FinanceDataFragment());
        fragments.add(new com.unitesoft.huanrong.widget.fragment.home.MarketCenterBroadcastFragment());
        chanelAdapter = new ChanelAdapter(getChildFragmentManager(), fragments, titles);
        vpFinanceNews.setOffscreenPageLimit(0);
        vpFinanceNews.setAdapter(chanelAdapter);
        indicator.setViewPager(vpFinanceNews);
        indicator.setCurrentItem(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
