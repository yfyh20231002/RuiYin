package com.tianjin.huanrong.widget.fragment.live;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tianjin.huanrong.R;
import com.tianjin.huanrong.manager.SharedPreferencesMgr;
import com.tianjin.huanrong.presenter.SynopsisPresenter;
import com.tianjin.huanrong.utils.ToastUtils;
import com.tianjin.huanrong.view.SynopsisView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 简介
 */
public class SynopsisFragment extends BaseFragment implements SynopsisView{


    @InjectView(R.id.image)
    ImageView image;
    SynopsisPresenter presenter;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null) {
            view = inflater.inflate(R.layout.fragment_synopsis, container, false);
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
        presenter=new SynopsisPresenter(this);
        String id = SharedPreferencesMgr.getZhiboshiid();
        presenter.postData(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void success(String imageurl) {
        Glide.with(getActivity()).load(imageurl).into(image);
    }

    @Override
    public void failed(String msg) {
        ToastUtils.showToast(getActivity(),msg);
    }


}
