package com.tianjin.huanrong.presenter;

import com.tianjin.huanrong.Bean.LoginBean;
import com.tianjin.huanrong.listener.OnLoginListener;
import com.tianjin.huanrong.model.MineModel;
import com.tianjin.huanrong.view.mine.LoginView;

/**
 * Created by Mr.zhang on 2017/8/10.
 */

public class LoginPresenter implements OnLoginListener{
    LoginView loginView;
    MineModel mineModel;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        mineModel=new MineModel(this);
    }
    public void login(){
        mineModel.login(loginView.zhanghao(),loginView.mima());
    }
    public void visitorLogin(){
        mineModel.visitorLogin(loginView.zhanghao(),loginView.mima());
    }
    @Override
    public void loginsuccess(LoginBean.DataBean dataBean) {
        loginView.loginsuccess(dataBean);
    }

    @Override
    public void visitorloginsuccess(LoginBean.DataBean dataBean) {
        loginView.visitorloginsuccess(dataBean);
    }

    @Override
    public void loginfailed(String msg) {
        loginView.loginfailed(msg);
    }

    @Override
    public void showMsg(String msg) {
        loginView.loginfailed(msg);
    }
    @Override
    public void showProgress(){
        loginView.showProgress();
    }
}
