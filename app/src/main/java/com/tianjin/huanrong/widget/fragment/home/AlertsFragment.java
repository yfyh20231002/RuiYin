package com.tianjin.huanrong.widget.fragment.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianjin.huanrong.R;
import com.tianjin.huanrong.widget.adapter.ChanelAdapter;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 快讯标签下的财经快讯
 */
public class AlertsFragment extends Fragment {


    @InjectView(R.id.indicator)
    TabPageIndicator indicator;
    @InjectView(R.id.vpFinanceNews)
    ViewPager vpFinanceNews;

    private List<Fragment> fragments = new ArrayList<>();
    private ChanelAdapter chanelAdapter;
    private String[] titles = new String[]{"经济数据", "市场播报"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context themeFragment = new ContextThemeWrapper(getActivity(), R.style.Theme_PageIndicatorFinanceNews);
        LayoutInflater localInflater = inflater.cloneInContext(themeFragment);
        View view = localInflater.inflate(R.layout.fragment_alerts, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }

    private void init() {
        fragments.clear();
        fragments.add(new com.tianjin.huanrong.widget.fragment.home.FinanceDataFragment());
        fragments.add(new com.tianjin.huanrong.widget.fragment.home.MarketCenterBroadcastFragment());
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
