package com.tianjin.huanrong.listener;


import com.tianjin.huanrong.Bean.LoginBean;

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
