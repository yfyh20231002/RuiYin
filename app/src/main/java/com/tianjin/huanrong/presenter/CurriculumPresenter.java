package com.tianjin.huanrong.presenter;

import com.tianjin.huanrong.Bean.CurriculumBean;
import com.tianjin.huanrong.listener.OnCurriculumListener;
import com.tianjin.huanrong.model.CurriculumModel;
import com.tianjin.huanrong.view.CurriculumView;

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
