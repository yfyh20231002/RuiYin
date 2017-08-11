package com.example.shichang393.ruiyin.widget.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shichang393.ruiyin.Bean.LiveBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.manager.SharedPreferencesMgr;
import com.example.shichang393.ruiyin.presenter.LivePresenter;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.view.LiveView;
import com.example.shichang393.ruiyin.widget.activity.live.StudioActivity;
import com.example.shichang393.ruiyin.widget.activity.mine.LoginActivity;
import com.example.shichang393.ruiyin.widget.adapter.home.LiveAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 直播
 */
public class LiveFragment extends Fragment implements LiveView {


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    LivePresenter presenter;
    LiveAdapter adapter;
    Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview();
        initdata();
    }

    private void initview() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(manager);
    }

    private void initdata() {
        presenter = new LivePresenter(this);
        presenter.postData();
    }

    @Override
    public void livesuccess(final List<LiveBean.DataBean.LiveRoomsBaseInfoBean> list) {
        if (adapter == null) {
            adapter = new LiveAdapter(list,getActivity());
        } else {
            adapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new LiveAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positon) {
                String zhiboshiid = list.get(positon).getZhiboshiid();
                SharedPreferencesMgr.setZhiboshiid(zhiboshiid);
                if (TextUtils.isEmpty(SharedPreferencesMgr.getuserid())){
                    LoginActivity.startIntent(mContext);
                }else {
                    StudioActivity.startIntent(mContext);
                }
            }
        });
    }

    @Override
    public void livefailed(String message) {
        ToastUtils.showToast(getActivity(),message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
