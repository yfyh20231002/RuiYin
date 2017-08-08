package com.example.shichang393.ruiyin.presenter;

import com.example.shichang393.ruiyin.listener.OnSynopsisListener;
import com.example.shichang393.ruiyin.model.SynopsisModel;
import com.example.shichang393.ruiyin.view.SynopsisView;

/**
 * Created by Mr.zhang on 2017/7/21.
 */

public class SynopsisPresenter implements OnSynopsisListener {
    SynopsisView view;
    SynopsisModel model;

    public SynopsisPresenter(SynopsisView view) {
        this.view = view;
        model=new SynopsisModel(this);
    }
    public  void postData(String zhiboshiid){
        model.postData(zhiboshiid);
    }

    @Override
    public void success(String imageurl) {
        view.success(imageurl);
    }

    @Override
    public void failed(String msg) {
        view.failed(msg);
    }
}
