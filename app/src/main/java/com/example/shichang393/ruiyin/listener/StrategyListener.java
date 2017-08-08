package com.example.shichang393.ruiyin.listener;

import com.example.shichang393.ruiyin.Bean.StrategyBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/18.
 */

public interface StrategyListener {
    void success(List<StrategyBean.DataBean.PROPOSALBean> list);
    void failed(String msg);
}
