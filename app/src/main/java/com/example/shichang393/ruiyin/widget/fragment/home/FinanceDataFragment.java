package com.example.shichang393.ruiyin.widget.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shichang393.ruiyin.Bean.FlashBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.presenter.FlashPresenter;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.view.FlashView;
import com.example.shichang393.ruiyin.widget.adapter.home.FinanceNewsAdapter;
import com.example.shichang393.ruiyin.widget.view.DividerItemDecoration;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 经济数据
 */
public class FinanceDataFragment extends Fragment implements FlashView {
    FlashPresenter presenter;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    FinanceNewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_financedata, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FlashPresenter(this);
        presenter.setModel();
        presenter.getData("经济数据");
        initview();
    }

    private void initview() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recyclerview.setLayoutManager(manager);
        recyclerview.addItemDecoration(decoration);
    }


    @Override
    public void success(List<FlashBean.DataBean.DangtianshujuBean> list) {
        if (newsAdapter == null) {
            newsAdapter = new FinanceNewsAdapter(list);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(newsAdapter);
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
