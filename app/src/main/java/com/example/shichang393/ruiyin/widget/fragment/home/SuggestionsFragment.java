package com.example.shichang393.ruiyin.widget.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shichang393.ruiyin.Bean.SuggestionBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.presenter.SuggestionPresenter;
import com.example.shichang393.ruiyin.utils.ConstanceValue;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.view.SuggestionView;
import com.example.shichang393.ruiyin.widget.adapter.home.SuggestionAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 操作建议
 */
public class SuggestionsFragment extends Fragment implements SuggestionView {


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    SuggestionPresenter presenter;
    SuggestionAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suggestions, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new SuggestionPresenter(this);
        presenter.postData(ConstanceValue.DefaultUserId);
        initview();
    }

    private void initview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void success(List<SuggestionBean.DataBean.LIVEROOMSSTICKBean> list) {
        if (adapter == null) {
            adapter = new SuggestionAdapter(list);
        } else {
            adapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void failed(String message) {
        ToastUtils.showToast(getActivity(), message);
    }
}
