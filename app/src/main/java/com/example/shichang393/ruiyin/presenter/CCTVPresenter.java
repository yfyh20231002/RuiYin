package com.example.shichang393.ruiyin.presenter;

import com.example.shichang393.ruiyin.Bean.CCTVBean;
import com.example.shichang393.ruiyin.listener.OnCCTVListener;
import com.example.shichang393.ruiyin.model.CCTVModel;
import com.example.shichang393.ruiyin.view.CCTView;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public class CCTVPresenter implements OnCCTVListener {
    CCTView view;
    CCTVModel model;

    public CCTVPresenter(CCTView view) {
        this.view = view;
    }

    public void setModel(CCTVModel model) {
        this.model = model;
        model.setListener(this);
    }

    public void getData(String videotype) {
        model.getData(videotype);
    }

    @Override
    public void success(List<CCTVBean.DataBean> data) {
        view.success(data);
    }

    @Override
    public void failed(String message) {
        view.failed(message);
    }
}
