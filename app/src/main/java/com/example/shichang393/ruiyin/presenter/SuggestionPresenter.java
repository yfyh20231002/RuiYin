package com.example.shichang393.ruiyin.presenter;

import com.example.shichang393.ruiyin.Bean.SuggestionBean;
import com.example.shichang393.ruiyin.listener.OnSuggestionListener;
import com.example.shichang393.ruiyin.model.SuggestionModel;
import com.example.shichang393.ruiyin.view.SuggestionView;

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
