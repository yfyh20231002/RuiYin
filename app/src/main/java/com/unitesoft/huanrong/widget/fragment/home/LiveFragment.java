package com.unitesoft.huanrong.widget.fragment.home;

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

import com.unitesoft.huanrong.Bean.LiveBean;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.manager.SharedPreferencesMgr;
import com.unitesoft.huanrong.presenter.LivePresenter;
import com.unitesoft.huanrong.utils.ToastUtils;
import com.unitesoft.huanrong.view.LiveView;
import com.unitesoft.huanrong.widget.activity.live.StudioActivity;
import com.unitesoft.huanrong.widget.activity.mine.LoginActivity;
import com.unitesoft.huanrong.widget.adapter.home.LiveAdapter;
import com.unitesoft.huanrong.widget.fragment.dialog.LoadDialog;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/8/31.
 * 直播室
 */

public class LiveFragment extends Fragment implements LiveView {


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    LivePresenter presenter;
    LiveAdapter adapter;
    Context mContext;

    private LoadDialog loadDialog;
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
        loadDialog=new LoadDialog();
        loadDialog.show(getChildFragmentManager(),"");
        presenter = new LivePresenter(this);
        presenter.postData();
    }

    @Override
    public void livesuccess(final List<LiveBean.DataBean.LiveRoomsBaseInfoBean> list) {
        loadDialog.dismiss();
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
                String title=list.get(positon).getZhiboshimingcheng();
                SharedPreferencesMgr.setZhiboshiid(zhiboshiid);
                if (TextUtils.isEmpty(SharedPreferencesMgr.getuserid())){
                    LoginActivity.startIntent(mContext,false);
                }else {
                    StudioActivity.startIntent(mContext,title);
                }
            }
        });
    }

    @Override
    public void livefailed(String message) {
        loadDialog.dismiss();
        ToastUtils.showToast(getActivity(),message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
