package com.unitesoft.huanrong.widget.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unitesoft.huanrong.Bean.FlashBean;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.presenter.FlashPresenter;
import com.unitesoft.huanrong.utils.ToastUtils;
import com.unitesoft.huanrong.view.FlashView;
import com.unitesoft.huanrong.widget.activity.home.NewsActivity;
import com.unitesoft.huanrong.widget.adapter.home.FlashAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 首页里面的财经快讯
 */
public class FlashFragment extends Fragment implements FlashView {
    FlashPresenter presenter;
    List<FlashBean.DataBean.DangtianshujuBean> flist = new ArrayList<>();
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    FlashAdapter flashAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flash, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FlashPresenter(this);
        presenter.setModel();
        presenter.getData("市场播报#政经要闻");
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void success(List<FlashBean.DataBean.DangtianshujuBean> list) {
        flist.addAll(list);
        flash();
    }

    private void flash() {
        if (flashAdapter == null) {
            flashAdapter = new FlashAdapter(flist, getActivity());
        } else {
            flashAdapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(flashAdapter);
        flashAdapter.setItemClickLitener(new FlashAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if ("政经要闻".equals(flist.get(position).getClassname())) {
                    if (!TextUtils.isEmpty(flist.get(position).getNewsreferurl())) {
                        NewsActivity.startIntent(getActivity(), flist.get(position).getNewsreferurl());
                    }
                }
            }
        });
    }

    @Override
    public void failed(String errormessage) {
        ToastUtils.showToast(getActivity(), errormessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
