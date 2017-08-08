package com.example.shichang393.ruiyin.presenter;

import com.example.shichang393.ruiyin.Bean.CurriculumBean;
import com.example.shichang393.ruiyin.listener.OnCurriculumListener;
import com.example.shichang393.ruiyin.model.CurriculumModel;
import com.example.shichang393.ruiyin.view.CurriculumView;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/21.
 */

public class CurriculumPresenter implements OnCurriculumListener {
    CurriculumView view;
    CurriculumModel model;

    public CurriculumPresenter(CurriculumView view) {
        this.view = view;
        model=new CurriculumModel(this);
    }

    public void postData(String zhiboshiid){
        model.postData(zhiboshiid);
    }


    @Override
    public void success(List<CurriculumBean.PaibanBean> list, List<CurriculumBean.YhtxBean> yhtx) {
        view.success(list,yhtx);
    }

    @Override
    public void failed(String msg) {
        view.failed(msg);
    }
}
