package com.tianjin.huanrong.presenter;

import com.tianjin.huanrong.Bean.SuggestionBean;
import com.tianjin.huanrong.listener.OnSuggestionListener;
import com.tianjin.huanrong.model.SuggestionModel;
import com.tianjin.huanrong.view.SuggestionView;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/10.
 */

public class SuggestionPresenter implements OnSuggestionListener{
    SuggestionModel model;
    SuggestionView view;

    public SuggestionPresenter(SuggestionView view) {
        this.view = view;
        model=new SuggestionModel(this);
    }

    public void postData(String userId){
        model.postData(userId);
    }
    @Override
    public void success(List<SuggestionBean.DataBean.LIVEROOMSSTICKBean> list) {
        view.success(list);
    }

    @Override
    public void failed(String message) {
        view.failed(message);
    }
}
