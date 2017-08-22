package com.unitesoft.huanrong.widget.fragment.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unitesoft.huanrong.Bean.FlashBean;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.presenter.FlashPresenter;
import com.unitesoft.huanrong.utils.ToastUtils;
import com.unitesoft.huanrong.view.FlashView;
import com.unitesoft.huanrong.widget.adapter.home.FinanceNewsAdapter;
import com.unitesoft.huanrong.widget.fragment.dialog.LoadDialog;
import com.unitesoft.huanrong.widget.fragment.live.BaseFragment;
import com.unitesoft.huanrong.widget.view.DividerItemDecoration;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 经济数据
 */
public class FinanceDataFragment extends BaseFragment implements FlashView {
    FlashPresenter presenter;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    FinanceNewsAdapter newsAdapter;

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
            view = inflater.inflate(R.layout.fragment_financedata, container, false);
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
        presenter = new FlashPresenter(this);
        presenter.setModel();
        presenter.getData("经济数据");
        initview();
    }

    private void initview() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        DividerItemDecoration decoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST);
        recyclerview.setLayoutManager(manager);
        recyclerview.addItemDecoration(decoration);
    }


    @Override
    public void success(List<FlashBean.DataBean.DangtianshujuBean> list) {
        loadDialog.dismiss();
        if (newsAdapter == null) {
            newsAdapter = new FinanceNewsAdapter(list);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(newsAdapter);
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
