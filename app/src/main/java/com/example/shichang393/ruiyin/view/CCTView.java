package com.example.shichang393.ruiyin.view;

import com.example.shichang393.ruiyin.Bean.CCTVBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public interface CCTView {
    void success(List<CCTVBean.DataBean> data);
    void failed(String message);
}
