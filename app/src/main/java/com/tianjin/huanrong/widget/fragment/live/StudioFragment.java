package com.tianjin.huanrong.widget.fragment.live;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.xtablayout.XTabLayout;
import com.tianjin.huanrong.R;
import com.tianjin.huanrong.widget.adapter.ChanelAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 直播的详情界面
 */
public class StudioFragment extends Fragment {

    @InjectView(R.id.tablayout)
    XTabLayout tablayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    String[] strings=new String[]{"互动","策略","课程表","简介"};
    List<Fragment> list=new ArrayList<>();
    ChanelAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_studio, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list.add(new InteractionFragment());
        list.add(new StrategyFragment());
//        list.add(new NoteFragment());
        list.add(new CurriculumFragment());
        list.add(new SynopsisFragment());
        adapter=new ChanelAdapter(getChildFragmentManager(),list,strings);
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(3);
        tablayout.setupWithViewPager(viewpager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
