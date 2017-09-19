package com.unitesoft.huanrong.presenter.mine;


import com.unitesoft.huanrong.listener.mine.OnSMSListener;
import com.unitesoft.huanrong.model.mine.SMSModel;
import com.unitesoft.huanrong.view.mine.SMSView;

/**
 * Created by Mr.zhang on 2017/8/11.
 */

public class SMSPresenter implements OnSMSListener {
    SMSView smsView;
    SMSModel smsModel;

    public SMSPresenter(SMSView smsView) {
        this.smsView = smsView;
        smsModel = new SMSModel(this);
    }

    public void sendValidateCode() {
        smsModel.sendValidateCode(smsView.getUserphone(),smsView.getSource());
    }

    /**
     * 重置密码
     */
    public void checkValidate() {
        smsModel.checkValidate(smsView.getUserphone(), smsView.getValidateCode(), smsView.getSendValidateCode(), smsView.getPassword());
    }

    /**
     * 注册
     */
    public void zhuce() {
        smsModel.useRegister(smsView.getUserphone(), smsView.getValidateCode(), smsView.getSendValidateCode(),smsView.getPassword());
    }


    @Override
    public void checkValidateSuccess(String sendMsg) {
        smsView.checkValidateSuccess(sendMsg);
    }

    @Override
    public void failed(String msg) {
        smsView.failed(msg);
    }

    @Override
    public void buttonChange(Boolean clickAble, String showText) {
        smsView.buttonChange(clickAble, showText);
    }

    @Override
    public void resetSuccess() {
        smsView.resetSuccess();
    }

    @Override
    public void registerSuccess() {
        smsView.registerSuccess();
    }
}
