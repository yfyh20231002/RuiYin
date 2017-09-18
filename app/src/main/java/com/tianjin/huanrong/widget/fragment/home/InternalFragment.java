package com.tianjin.huanrong.widget.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianjin.huanrong.Bean.IptMsgBean;
import com.tianjin.huanrong.R;
import com.tianjin.huanrong.presenter.IptMsgPresenter;
import com.tianjin.huanrong.utils.ToastUtils;
import com.tianjin.huanrong.view.IptMsgView;
import com.tianjin.huanrong.widget.activity.home.IptMsgActivity;
import com.tianjin.huanrong.widget.adapter.home.InternalAdpater;
import com.tianjin.huanrong.widget.fragment.dialog.LoadDialog;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 策略
 */
public class InternalFragment extends Fragment implements IptMsgView {


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    IptMsgPresenter presenter;
    InternalAdpater adpater;
    private LoadDialog loadDialog;
    public InternalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_internal, container, false);
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
        loadDialog=new LoadDialog();
        loadDialog.show(getChildFragmentManager(),"");
        presenter = new IptMsgPresenter(this);
        presenter.getData("20", 1, 100);
    }

    @Override
    public void success(final List<IptMsgBean> list) {
        loadDialog.dismiss();
        if (adpater==null){
            adpater=new InternalAdpater(list,R.mipmap.celue_img);
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
        loadDialog.dismiss();
        ToastUtils.showToast(getActivity(),message);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
