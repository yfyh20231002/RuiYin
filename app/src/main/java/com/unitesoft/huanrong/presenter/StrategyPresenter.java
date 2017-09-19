package com.unitesoft.huanrong.presenter;

import com.unitesoft.huanrong.Bean.StrategyBean;
import com.unitesoft.huanrong.listener.StrategyListener;
import com.unitesoft.huanrong.model.StrategyModel;
import com.unitesoft.huanrong.view.StrategyView;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/18.
 */

public class StrategyPresenter implements StrategyListener {
    StrategyView view;
    StrategyModel model;

    public StrategyPresenter(StrategyView view) {
        this.view = view;
        model=new StrategyModel(this);
    }

    public void postData(String zhiboshiid){
        model.postData(zhiboshiid);
    }

    @Override
    public void success(List<StrategyBean.DataBean.PROPOSALBean> list) {
        view.success(list);
    }

    @Override
    public void failed(String msg) {
        view.failed(msg);
    }
}
