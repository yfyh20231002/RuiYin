package com.example.shichang393.ruiyin.presenter;

import com.example.shichang393.ruiyin.Bean.LiveBean;
import com.example.shichang393.ruiyin.listener.OnLiveListener;
import com.example.shichang393.ruiyin.model.LiveModel;
import com.example.shichang393.ruiyin.view.LiveView;

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
