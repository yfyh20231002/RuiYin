package com.example.shichang393.ruiyin.presenter;

import com.example.shichang393.ruiyin.Bean.FlashBean;
import com.example.shichang393.ruiyin.listener.OnFlashListener;
import com.example.shichang393.ruiyin.model.FlashModel;
import com.example.shichang393.ruiyin.view.FlashView;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/23.
 */

public class FlashPresenter implements OnFlashListener{
    FlashModel model;
    FlashView view;

    public FlashPresenter(FlashView view) {
        this.view = view;
    }
    public  void setModel(){
        this.model=new FlashModel(this);
    }
    public void getData(String dataType) {
        model.getData(dataType);
    }

    @Override
    public void success(List<FlashBean.DataBean.DangtianshujuBean> list) {
        view.success(list);
    }

    @Override
    public void failed(String errormessage) {
        view.failed(errormessage);
    }
}
