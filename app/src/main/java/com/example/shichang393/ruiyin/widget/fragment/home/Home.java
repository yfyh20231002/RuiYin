package com.example.shichang393.ruiyin.widget.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shichang393.ruiyin.Bean.IptMsgBean;
import com.example.shichang393.ruiyin.Bean.LiveBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.manager.SharedPreferencesMgr;
import com.example.shichang393.ruiyin.model.BannerMode;
import com.example.shichang393.ruiyin.presenter.IptMsgPresenter;
import com.example.shichang393.ruiyin.presenter.LivePresenter;
import com.example.shichang393.ruiyin.utils.CommonUtil;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.view.IptMsgView;
import com.example.shichang393.ruiyin.view.LiveView;
import com.example.shichang393.ruiyin.widget.activity.home.IptMsgActivity;
import com.example.shichang393.ruiyin.widget.activity.live.StudioActivity;
import com.example.shichang393.ruiyin.widget.adapter.ChanelAdapter;
import com.example.shichang393.ruiyin.widget.adapter.home.HomeLiveAdapter;
import com.example.shichang393.ruiyin.widget.adapter.home.IptMsgAdapter;
import com.example.shichang393.ruiyin.widget.view.NoScrollViewPager;
import com.example.shichang393.ruiyin.widget.view.SpaceItemDecoration;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.example.shichang393.ruiyin.R.id.recyclerview;

/**
 * Created by Mr.zhang on 2017/7/4.
 */

public class Home extends Fragment implements IptMsgView, LiveView {
    @InjectView(R.id.listview)
    ListView listview;

    LayoutInflater layoutInflater;
    View header, footer;

    /**
     * header里面的view
     */
    Banner banner;

    /**
     * footer里面的view
     */
    RecyclerView recyclerView;
    TabLayout tablayout;
    NoScrollViewPager vp;


    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles = new String[]{"财经快讯", "财经日历", "交易公告"};

    IptMsgPresenter presenter;
    IptMsgAdapter adapter;
    private ChanelAdapter chanelAdapter;


    LivePresenter livePresenter;
    HomeLiveAdapter liveAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutInflater = LayoutInflater.from(getActivity());
        initHeader();
        initFooter();
        initListview();
        initrecyclerview();
    }


    private void initHeader() {
        header = layoutInflater.inflate(R.layout.home_head, null);
        banner = (Banner) header.findViewById(R.id.banner);
        BannerMode.getData(banner, getActivity());
    }

    private void initFooter() {
        footer = layoutInflater.inflate(R.layout.home_foot, null);
        recyclerView = (RecyclerView) footer.findViewById(recyclerview);
        tablayout = (TabLayout) footer.findViewById(R.id.tablayout);
        vp = (NoScrollViewPager) footer.findViewById(R.id.vp);
//        ViewGroup.LayoutParams layoutParams = vp.getLayoutParams();
//        layoutParams.height = getActivity().getResources().getDisplayMetrics().heightPixels / 2;
//        vp.setLayoutParams(layoutParams);
        listview.postDelayed(new Runnable() {
            @Override
            public void run() {
                vp.setTagHeight(listview.getMeasuredHeight() - tablayout.getMeasuredHeight() );
            }
        }, 1000);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(CommonUtil.dip2px(11)));

        /**
         * 第二个tablayout的事件
         */
        twoTabEvent();
    }


    private void twoTabEvent() {
        fragments.clear();
        fragments.add(new FlashFragment());
        fragments.add(new CalendarFragment());
        fragments.add(new NoticeFragment());
        chanelAdapter = new ChanelAdapter(getChildFragmentManager(), fragments, titles);
        vp.setNoScroll(true);
        vp.setAdapter(chanelAdapter);
        vp.setOffscreenPageLimit(3);
        tablayout.setupWithViewPager(vp);
    }


    private void initListview() {
        presenter = new IptMsgPresenter(this);
        presenter.getData("29,32,33", 1, 3);
        listview.addHeaderView(header);
        listview.addFooterView(footer);
    }

    private void initrecyclerview() {
        livePresenter = new LivePresenter(this);
        livePresenter.postData();
    }

    @Override
    public void success(final List<IptMsgBean> list) {
        if (adapter == null) {
            adapter = new IptMsgAdapter(list, getActivity());
        } else {
            adapter.notifyDataSetChanged();
        }
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String path = list.get(position - 1).getImage();
                if (path.equals("/UploadFiles/Images/20170626093013_548.pdf")) {
                    return;
                }
                IptMsgActivity.startIntent(getActivity(), path);
            }
        });
    }

    @Override
    public void faild(String message) {
        ToastUtils.showToast(getActivity(), message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void livesuccess(final List<LiveBean.DataBean.LiveRoomsBaseInfoBean> list) {
        if (liveAdapter == null) {
            liveAdapter = new HomeLiveAdapter(list);
        } else {
            liveAdapter.notifyDataSetChanged();
        }
        recyclerView.setAdapter(liveAdapter);
        liveAdapter.setOnItemClickListener(new HomeLiveAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positon) {
                String zhiboshiid = list.get(positon).getZhiboshiid();
                SharedPreferencesMgr.setZhiboshiid(zhiboshiid);
                StudioActivity.startIntent(getActivity());
            }
        });
    }

    @Override
    public void livefailed(String message) {
        ToastUtils.showToast(getActivity(), message);
    }
}
