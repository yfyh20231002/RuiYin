package com.tianjin.huanrong.listener;


import com.tianjin.huanrong.Bean.CCTVBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public interface OnCCTVListener {
    void success(List<CCTVBean.DataBean> data);
    void failed(String message);
}
