package com.example.shichang393.ruiyin.presenter;

import com.example.shichang393.ruiyin.Bean.LoginBean;
import com.example.shichang393.ruiyin.listener.OnLoginListener;
import com.example.shichang393.ruiyin.model.MineModel;
import com.example.shichang393.ruiyin.view.mine.LoginView;

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
