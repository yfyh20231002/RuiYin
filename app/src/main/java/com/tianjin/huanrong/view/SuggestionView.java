package com.tianjin.huanrong.view;

import com.tianjin.huanrong.Bean.SuggestionBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/10.
 */

public interface SuggestionView {
    void success(List<SuggestionBean.DataBean.LIVEROOMSSTICKBean> list);
    void failed(String message);
}
