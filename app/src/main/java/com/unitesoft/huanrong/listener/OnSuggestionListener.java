package com.unitesoft.huanrong.listener;


import com.unitesoft.huanrong.Bean.SuggestionBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/10.
 */

public interface OnSuggestionListener {
    void success(List<SuggestionBean.DataBean.LIVEROOMSSTICKBean> list);
    void failed(String message);
}
