package com.unitesoft.huanrong.listener.mine;

/**
 * Created by Mr.zhang on 2017/8/11.
 */

public interface OnSMSListener {
    void checkValidateSuccess(String sendMsg);

    void failed(String msg);

    void buttonChange(Boolean clickAble, String showText);

    void resetSuccess();

    void registerSuccess();
}
