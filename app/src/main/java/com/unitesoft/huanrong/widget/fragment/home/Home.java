package com.unitesoft.huanrong.widget.fragment.home;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.unitesoft.huanrong.Bean.IptMsgBean;
import com.unitesoft.huanrong.Bean.LiveBean;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.manager.SharedPreferencesMgr;
import com.unitesoft.huanrong.model.BannerMode;
import com.unitesoft.huanrong.presenter.IptMsgPresenter;
import com.unitesoft.huanrong.presenter.LivePresenter;
import com.unitesoft.huanrong.utils.CommonUtil;
import com.unitesoft.huanrong.utils.ToastUtils;
import com.unitesoft.huanrong.view.IptMsgView;
import com.unitesoft.huanrong.view.LiveView;
import com.unitesoft.huanrong.widget.activity.home.IptMsgActivity;
import com.unitesoft.huanrong.widget.activity.live.StudioActivity;
import com.unitesoft.huanrong.widget.activity.mine.LoginActivity;
import com.unitesoft.huanrong.widget.adapter.ChanelAdapter;
import com.unitesoft.huanrong.widget.adapter.home.HomeLiveAdapter;
import com.unitesoft.huanrong.widget.adapter.home.IptMsgAdapter;
import com.unitesoft.huanrong.widget.fragment.dialog.LoadDialog;
import com.unitesoft.huanrong.widget.fragment.live.BaseFragment;
import com.unitesoft.huanrong.widget.view.NoScrollViewPager;
import com.unitesoft.huanrong.widget.view.SpaceItemDecoration;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.unitesoft.huanrong.R.id.recyclerview;

/**
 * Created by Mr.zhang on 2017/7/4.
 */

public class Home extends BaseFragment implements IptMsgView, LiveView {
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
    Context mContext;
    private LoadDialog loadDialog;
    private  View view;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.home, container, false);
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
        layoutInflater = LayoutInflater.from(mContext);
        initHeader();
        initFooter();
        initListview();
        initrecyclerview();
    }

    private void initHeader() {
        header = layoutInflater.inflate(R.layout.home_head, null);
        banner = (Banner) header.findViewById(R.id.banner);
        BannerMode.getData(banner, mContext);
    }

    private void initFooter() {
        footer = layoutInflater.inflate(R.layout.home_foot, null);
        recyclerView = (RecyclerView) footer.findViewById(recyclerview);
        tablayout = (TabLayout) footer.findViewById(R.id.tablayout);
        vp = (NoScrollViewPager) footer.findViewById(R.id.vp);
//        ViewGroup.LayoutParams layoutParams = vp.getLayoutParams();
//        layoutParams.height = mContext.getResources().getDisplayMetrics().heightPixels / 2;
//        vp.setLayoutParams(layoutParams);
        listview.postDelayed(new Runnable() {
            @Override
            public void run() {
                vp.setTagHeight(listview.getMeasuredHeight() - tablayout.getMeasuredHeight());
            }
        }, 1000);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
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
        fragments.add(new com.unitesoft.huanrong.widget.fragment.home.NoticeFragment());
        chanelAdapter = new ChanelAdapter(getChildFragmentManager(), fragments, titles);
        vp.setNoScroll(true);
        vp.setAdapter(chanelAdapter);
        vp.setOffscreenPageLimit(3);
        tablayout.setupWithViewPager(vp);
    }


    private void initListview() {
        loadDialog = new LoadDialog();
        loadDialog.show(getChildFragmentManager(), "");
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
        loadDialog.dismiss();
        if (adapter == null) {
            adapter = new IptMsgAdapter(list, mContext);
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
                IptMsgActivity.startIntent(mContext, path);
            }
        });
    }

    @Override
    public void faild(String message) {
        loadDialog.dismiss();
        ToastUtils.showToast(mContext, message);
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
                if (TextUtils.isEmpty(SharedPreferencesMgr.getuserid())) {
                    LoginActivity.startIntent(mContext,false);
                } else {
                    StudioActivity.startIntent(mContext);
                }
            }
        });
    }

    @Override
    public void livefailed(String message) {
        ToastUtils.showToast(mContext, message);
    }


}
