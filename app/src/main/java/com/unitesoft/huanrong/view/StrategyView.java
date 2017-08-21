package com.unitesoft.huanrong.view;

import com.unitesoft.huanrong.Bean.StrategyBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/18.
 */

public interface StrategyView {
    void success(List<StrategyBean.DataBean.PROPOSALBean> list);
    void failed(String msg);
}
