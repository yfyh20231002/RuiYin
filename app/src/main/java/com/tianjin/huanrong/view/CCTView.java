package com.tianjin.huanrong.view;


import com.tianjin.huanrong.Bean.CCTVBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public interface CCTView {
    void success(List<CCTVBean.DataBean> data);
    void failed(String message);
}
