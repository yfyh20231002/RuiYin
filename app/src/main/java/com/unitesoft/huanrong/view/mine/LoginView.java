package com.unitesoft.huanrong.view.mine;


import com.unitesoft.huanrong.Bean.LoginBean;

/**
 * Created by Mr.zhang on 2017/8/10.
 */

public interface LoginView {
    String zhanghao();
    String mima();
    void loginsuccess(LoginBean.DataBean dataBean);
    void  visitorloginsuccess(LoginBean.DataBean dataBean);
    void loginfailed(String msg);
    void showProgress();
}
