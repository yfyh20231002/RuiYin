package com.unitesoft.huanrong.widget.fragment.home;


import android.content.Context;
import android.os.Bundle;
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
import com.unitesoft.huanrong.widget.adapter.home.DataAdapter;
import com.unitesoft.huanrong.widget.fragment.dialog.LoadDialog;
import com.unitesoft.huanrong.widget.fragment.live.BaseFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 数据
 */
public class DataFragment extends BaseFragment implements IptMsgView {


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;

    DataAdapter adapter;
    IptMsgPresenter presenter;

    private LoadDialog loadDialog;
    private  View view;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_data, container, false);
            isPrepared = true;
            ButterKnife.inject(this, view);
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }


    @Override
    protected void lazyLoad() {
        if(!isPrepared || !isVisible) {
            return;
        }
        initview();
        initData();
    }
    private void initview() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
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
                IptMsgActivity.startIntent(mContext, path);
            }
        });
    }

    @Override
    public void faild(String message) {
        loadDialog.dismiss();
        ToastUtils.showToast(mContext,message);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
