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
import com.unitesoft.huanrong.widget.adapter.home.InternalAdpater;
import com.unitesoft.huanrong.widget.fragment.dialog.LoadDialog;
import com.unitesoft.huanrong.widget.fragment.live.BaseFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * EIA
 */
public class EIAFragment extends BaseFragment implements IptMsgView {


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    IptMsgPresenter presenter;
    InternalAdpater adpater;

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
            view = inflater.inflate(R.layout.fragment_eia, container, false);
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
        presenter.getData("33", 1, 100);
    }

    @Override
    public void success(final List<IptMsgBean> list) {
        loadDialog.dismiss();
        if (adpater==null){
            adpater=new InternalAdpater(list,R.mipmap.eia_img);
        }else {
            adpater.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adpater);
        adpater.setOnItemClickListener(new InternalAdpater.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                IptMsgActivity.startIntent(mContext,list.get(position).getImage());
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
