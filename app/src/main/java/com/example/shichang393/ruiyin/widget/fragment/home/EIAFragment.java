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
import com.example.shichang393.ruiyin.widget.adapter.home.InternalAdpater;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EIAFragment extends Fragment implements IptMsgView {


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    IptMsgPresenter presenter;
    InternalAdpater adpater;
    public EIAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eia, container, false);
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
        presenter.getData("33", 1, 100);
    }

    @Override
    public void success(final List<IptMsgBean> list) {
        if (adpater==null){
            adpater=new InternalAdpater(list,R.mipmap.eia_img);
        }else {
            adpater.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adpater);
        adpater.setOnItemClickListener(new InternalAdpater.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                IptMsgActivity.startIntent(getActivity(),list.get(position).getImage());
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
