package com.unitesoft.huanrong.widget.fragment.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unitesoft.huanrong.Bean.SuggestionBean;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.manager.SharedPreferencesMgr;
import com.unitesoft.huanrong.presenter.SuggestionPresenter;
import com.unitesoft.huanrong.utils.ToastUtils;
import com.unitesoft.huanrong.view.SuggestionView;
import com.unitesoft.huanrong.widget.activity.mine.LoginActivity;
import com.unitesoft.huanrong.widget.adapter.home.SuggestionAdapter;
import com.unitesoft.huanrong.widget.fragment.dialog.LoadDialog;
import com.unitesoft.huanrong.widget.fragment.live.BaseFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 操作建议
 */
public class SuggestionsFragment extends BaseFragment implements SuggestionView {


    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    SuggestionPresenter presenter;
    SuggestionAdapter adapter;
    private LoadDialog loadDialog;
    private Context mContext;


    private View view;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_suggestions, container, false);
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
        String userid = SharedPreferencesMgr.getuserid();
        if (TextUtils.isEmpty(userid)) {
            LoginActivity.startIntent(mContext,false);
        } else {
            loadDialog = new LoadDialog();
            loadDialog.show(getChildFragmentManager(), "");
            presenter = new SuggestionPresenter(this);
            presenter.postData(userid);
        }
        initview();
    }

    private void initview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void success(List<SuggestionBean.DataBean.LIVEROOMSSTICKBean> list) {
        loadDialog.dismiss();
        if (adapter == null) {
            adapter = new SuggestionAdapter(list);
        } else {
            adapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void failed(String message) {
        loadDialog.dismiss();
        ToastUtils.showToast(mContext, message);
    }


}
