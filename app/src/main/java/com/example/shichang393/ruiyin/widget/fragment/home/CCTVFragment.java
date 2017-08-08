package com.example.shichang393.ruiyin.widget.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shichang393.ruiyin.Bean.CCTVBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.model.CCTVModel;
import com.example.shichang393.ruiyin.presenter.CCTVPresenter;
import com.example.shichang393.ruiyin.utils.ConstanceValue;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.view.CCTView;
import com.example.shichang393.ruiyin.widget.activity.home.PlayCCTVActivity;
import com.example.shichang393.ruiyin.widget.adapter.home.CCTVAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CCTVFragment extends Fragment implements CCTView {


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    CCTVPresenter presenter;
    CCTVAdapter adapter;
    public CCTVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cctv, container, false);
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
        presenter = new CCTVPresenter(this);
        presenter.setModel(new CCTVModel());
        presenter.getData("鲸鱼APP视频");
    }

    @Override
    public void success(final List<CCTVBean.DataBean> data) {
        if (adapter == null) {
            adapter = new CCTVAdapter(data);
        } else {
            adapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new CCTVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                List<String> urls=new ArrayList<String>();
                List<String> images=new ArrayList<String>();
                for (int i = 0; i < data.size(); i++) {
                    urls.add(data.get(i).getVideoUrl());
                    images.add(ConstanceValue.iptmsgurl+data.get(i).getVideoPicture());
                }
                PlayCCTVActivity.startIntent(getActivity(),urls,images);
            }
        });
    }

    @Override
    public void failed(String message) {
        ToastUtils.showToast(getActivity(), message);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
