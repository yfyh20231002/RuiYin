package com.example.shichang393.ruiyin.view.mine;

import com.example.shichang393.ruiyin.Bean.LoginBean;

/**
 * Created by Mr.zhang on 2017/8/10.
 */

public interface LoginView {
    String zhanghao();
    String mima();
    void loginsuccess(LoginBean.DataBean dataBean);
    void loginfailed(String msg);
    void showProgress();
}
