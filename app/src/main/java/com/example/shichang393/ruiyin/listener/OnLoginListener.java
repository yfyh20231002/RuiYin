package com.example.shichang393.ruiyin.listener;

import com.example.shichang393.ruiyin.Bean.LoginBean;

/**
 * Created by Mr.zhang on 2017/8/10.
 */

public interface OnLoginListener {
    void loginsuccess(LoginBean.DataBean dataBean);
    void visitorloginsuccess(LoginBean.DataBean dataBean);
    void loginfailed(String msg);
    void showMsg(String msg);
    void showProgress();
}
