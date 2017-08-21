package com.unitesoft.huanrong.presenter;

import com.unitesoft.huanrong.Bean.NoticeBean;
import com.unitesoft.huanrong.listener.OnNoticeListener;
import com.unitesoft.huanrong.model.NoticeModel;
import com.unitesoft.huanrong.view.NoticeView;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/26.
 */

public class NoticePresenter implements OnNoticeListener {
    NoticeView view;
    NoticeModel model;

    public NoticePresenter(NoticeView view) {
        this.view = view;
    }

    public void setModel(){
        this.model=new NoticeModel(this);
    }

    public void getData(int id,int index, int size){
        model.getData(id,index,size);
    }
    @Override
    public void success(List<NoticeBean> list) {
        view.success(list);
    }

    @Override
    public void failed(String errormessage) {
        view.failed(errormessage);
    }
}
