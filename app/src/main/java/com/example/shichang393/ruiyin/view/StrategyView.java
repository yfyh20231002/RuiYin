package com.example.shichang393.ruiyin.view;

import com.example.shichang393.ruiyin.Bean.StrategyBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/18.
 */

public interface StrategyView {
    void success(List<StrategyBean.DataBean.PROPOSALBean> list);
    void failed(String msg);
}
