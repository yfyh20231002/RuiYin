package com.unitesoft.huanrong.listener;


import com.unitesoft.huanrong.Bean.StrategyBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/18.
 */

public interface StrategyListener {
    void success(List<StrategyBean.DataBean.PROPOSALBean> list);
    void failed(String msg);
}
