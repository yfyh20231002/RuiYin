package com.unitesoft.huanrong.widget.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unitesoft.huanrong.Bean.IptMsgBean;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.presenter.IptMsgPresenter;
import com.unitesoft.huanrong.utils.ToastUtils;
import com.unitesoft.huanrong.view.IptMsgView;
import com.unitesoft.huanrong.widget.activity.home.IptMsgActivity;
import com.unitesoft.huanrong.widget.adapter.home.InternalAdpater;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeiNongFragment extends Fragment implements IptMsgView {


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    IptMsgPresenter presenter;
    InternalAdpater adpater;
    public FeiNongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feinong, container, false);
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
        presenter.getData("32", 1, 100);
    }

    @Override
    public void success(final List<IptMsgBean> list) {
        if (adpater==null){
            adpater=new InternalAdpater(list,R.mipmap.feinong_img);
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
