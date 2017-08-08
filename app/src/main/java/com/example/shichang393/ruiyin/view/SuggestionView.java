package com.example.shichang393.ruiyin.view;

import com.example.shichang393.ruiyin.Bean.SuggestionBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/10.
 */

public interface SuggestionView {
    void success(List<SuggestionBean.DataBean.LIVEROOMSSTICKBean> list);
    void failed(String message);
}
