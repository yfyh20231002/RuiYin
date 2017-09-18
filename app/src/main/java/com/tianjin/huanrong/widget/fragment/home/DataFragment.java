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
import com.tianjin.huanrong.widget.adapter.home.DataAdapter;
import com.tianjin.huanrong.widget.fragment.dialog.LoadDialog;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 数据
 */
public class DataFragment extends Fragment implements IptMsgView {


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;

    DataAdapter adapter;
    IptMsgPresenter presenter;

    private LoadDialog loadDialog;
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
        loadDialog=new LoadDialog();
        loadDialog.show(getChildFragmentManager(),"");
        presenter = new IptMsgPresenter(this);
        presenter.getData("29", 1, 100);
    }

    @Override
    public void success(final List<IptMsgBean> list) {
        loadDialog.dismiss();
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
        loadDialog.dismiss();
        ToastUtils.showToast(getActivity(),message);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
