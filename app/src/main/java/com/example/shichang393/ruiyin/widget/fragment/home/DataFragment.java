package com.example.shichang393.ruiyin.widget.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shichang393.ruiyin.Bean.IptMsgBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.presenter.IptMsgPresenter;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.view.IptMsgView;
import com.example.shichang393.ruiyin.widget.activity.home.IptMsgActivity;
import com.example.shichang393.ruiyin.widget.adapter.home.DataAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends Fragment implements IptMsgView {


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;

    DataAdapter adapter;
    IptMsgPresenter presenter;
    public DataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview();
        initData();
    }
    private void initview() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(manager);
    }

    private void initData() {
        presenter = new IptMsgPresenter(this);
        presenter.getData("29", 1, 100);
    }

    @Override
    public void success(final List<IptMsgBean> list) {
        if (adapter == null) {
            adapter = new DataAdapter(list);
        } else {
            adapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String path=list.get(position).getImage();
                if (path.equals("/UploadFiles/Images/20170626093013_548.pdf")){return;}
                IptMsgActivity.startIntent(getActivity(), path);
            }
        });
    }

    @Override
    public void faild(String message) {
        ToastUtils.showToast(getActivity(),message);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
