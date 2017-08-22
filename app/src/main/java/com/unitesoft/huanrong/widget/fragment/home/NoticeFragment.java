package com.unitesoft.huanrong.widget.fragment.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unitesoft.huanrong.Bean.NoticeBean;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.presenter.NoticePresenter;
import com.unitesoft.huanrong.utils.ToastUtils;
import com.unitesoft.huanrong.view.NoticeView;
import com.unitesoft.huanrong.widget.activity.home.NoticeActivity;
import com.unitesoft.huanrong.widget.adapter.home.NoticeAdapter;
import com.unitesoft.huanrong.widget.fragment.dialog.LoadDialog;
import com.unitesoft.huanrong.widget.fragment.live.BaseFragment;
import com.unitesoft.huanrong.widget.view.DividerItemDecoration;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 交易公告
 */
public class NoticeFragment extends BaseFragment implements NoticeView {
    NoticePresenter presenter;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    NoticeAdapter adapter;
    private Context mContext;
    private LoadDialog loadDialog;
    private  View view;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_notice, container, false);
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
        loadDialog=new LoadDialog();
        loadDialog.show(getChildFragmentManager(),"");
        presenter = new NoticePresenter(this);
        presenter.setModel();
        presenter.getData(49,1,1000);
    }

    @Override
    public void success(final List<NoticeBean> list) {
        loadDialog.dismiss();
        if (adapter==null){
            adapter=new NoticeAdapter(list,mContext);
        }else {
            adapter.notifyDataSetChanged();
        }
        LinearLayoutManager manager=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerview.setLayoutManager(manager);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL_LIST));
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new NoticeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NoticeActivity.startIntent(mContext,list.get(position).getInfoID());
            }
        });
    }

    @Override
    public void failed(String errormessage) {
        loadDialog.dismiss();
        ToastUtils.showToast(mContext, errormessage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
