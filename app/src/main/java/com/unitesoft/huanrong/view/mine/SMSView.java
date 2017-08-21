package com.unitesoft.huanrong.view.mine;

/**
 * Created by Mr.zhang on 2017/8/11.
 */

public interface SMSView {
    // 获取手机号码
    String getUserphone();

    // 获取手机验证码
    String getValidateCode();

    // 获取已经发送的验证码
    String getSendValidateCode();
//    获取设置的密码
    String getPassword();

    void checkValidateSuccess(String sendMsg);

    void failed(String msg);

    void buttonChange(Boolean clickAble, String showText);
//    重置密码成功
    void resetSuccess();
//    注册成功
    void registerSuccess();
}
