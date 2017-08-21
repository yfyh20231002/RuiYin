package com.unitesoft.huanrong.listener;


import com.unitesoft.huanrong.Bean.CCTVBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public interface OnCCTVListener {
    void success(List<CCTVBean.DataBean> data);
    void failed(String message);
}
