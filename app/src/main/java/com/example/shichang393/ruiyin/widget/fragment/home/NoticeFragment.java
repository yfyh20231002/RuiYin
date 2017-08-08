package com.example.shichang393.ruiyin.widget.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shichang393.ruiyin.Bean.NoticeBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.presenter.NoticePresenter;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.view.NoticeView;
import com.example.shichang393.ruiyin.widget.activity.home.NoticeActivity;
import com.example.shichang393.ruiyin.widget.adapter.home.NoticeAdapter;
import com.example.shichang393.ruiyin.widget.view.DividerItemDecoration;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 交易公告
 */
public class NoticeFragment extends Fragment implements NoticeView {
    NoticePresenter presenter;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    NoticeAdapter adapter;
    public NoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new NoticePresenter(this);
        presenter.setModel();
        presenter.getData(49,1,1000);

    }

    @Override
    public void success(final List<NoticeBean> list) {
        if (adapter==null){
            adapter=new NoticeAdapter(list,getActivity());
        }else {
            adapter.notifyDataSetChanged();
        }
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(manager);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new NoticeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NoticeActivity.startIntent(getActivity(),list.get(position).getInfoID());
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
