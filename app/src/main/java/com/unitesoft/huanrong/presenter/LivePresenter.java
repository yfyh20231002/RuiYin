package com.unitesoft.huanrong.presenter;

import com.unitesoft.huanrong.Bean.LiveBean;
import com.unitesoft.huanrong.listener.OnLiveListener;
import com.unitesoft.huanrong.model.LiveModel;
import com.unitesoft.huanrong.view.LiveView;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public class LivePresenter implements OnLiveListener {
    LiveModel model;
    LiveView view;

    public LivePresenter(LiveView view) {
        this.view = view;
        model = new LiveModel(this);
    }
    public void postData(){
        model.postData();
    }

    @Override
    public void success(List<LiveBean.DataBean.LiveRoomsBaseInfoBean> list) {
        view.livesuccess(list);
    }

    @Override
    public void failed(String message) {
        view.livefailed(message);
    }
}
