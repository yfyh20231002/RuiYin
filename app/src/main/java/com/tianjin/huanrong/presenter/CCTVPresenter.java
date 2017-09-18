package com.tianjin.huanrong.presenter;

import com.tianjin.huanrong.Bean.CCTVBean;
import com.tianjin.huanrong.listener.OnCCTVListener;
import com.tianjin.huanrong.model.CCTVModel;
import com.tianjin.huanrong.view.CCTView;

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
