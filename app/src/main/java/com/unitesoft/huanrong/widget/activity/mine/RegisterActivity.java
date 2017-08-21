package com.unitesoft.huanrong.widget.activity.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.unitesoft.huanrong.widget.activity.mine.ForgotpwdDialog;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.presenter.mine.SMSPresenter;
import com.unitesoft.huanrong.utils.ToastUtils;
import com.unitesoft.huanrong.view.mine.SMSView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 注册和重置密码用一个activity
 */
public class RegisterActivity extends Activity implements SMSView {

    @InjectView(R.id.zhuce_back)
    ImageView zhuceBack;
    @InjectView(R.id.reset_relativelayout)
    RelativeLayout resetRelativelayout;
    @InjectView(R.id.btn_zhuce_exit)
    ImageButton btnZhuceExit;
    @InjectView(R.id.zhuce_edit_number)
    EditText zhuceEditNumber;
    @InjectView(R.id.imag_zhuce_yanzheng)
    ImageView imagZhuceYanzheng;
    @InjectView(R.id.zhuce_edit_yanzheng)
    EditText zhuceEditYanzheng;
    @InjectView(R.id.btn_zhuce_yzm)
    Button btnZhuceYzm;
    @InjectView(R.id.zhuce_edit_password)
    EditText zhuceEditPassword;
    @InjectView(R.id.btn_zhuce_sure)
    Button btnZhuceSure;
    @InjectView(R.id.register_linearlayout)
    LinearLayout registerLinearlayout;
    private int param;
    private String sendMsg; // 已经发送的短信验证码
    private SMSPresenter smsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        init();

    }

    private void init() {
        param = getIntent().getIntExtra("param", 1);
        if (0 == param) {
            registerLinearlayout.setBackgroundColor(Color.WHITE);
            resetRelativelayout.setVisibility(View.VISIBLE);
            btnZhuceExit.setVisibility(View.GONE);
            btnZhuceSure.setText("确定");
        } else {
            registerLinearlayout.setBackgroundResource(R.mipmap.login_bg);
            resetRelativelayout.setVisibility(View.GONE);
            btnZhuceExit.setVisibility(View.VISIBLE);
            btnZhuceSure.setText("注册");
        }
        smsPresenter = new SMSPresenter(this);
    }

    @OnClick(R.id.zhuce_back)
    public void onZhuceBackClicked() {
        finish();
    }

    @OnClick(R.id.btn_zhuce_exit)
    public void onBtnZhuceExitClicked() {
        finish();
    }

    @OnClick(R.id.btn_zhuce_yzm)
    public void onBtnZhuceYzmClicked() {

        btnZhuceYzm.setClickable(false);
        smsPresenter.sendValidateCode();

    }

    @OnClick(R.id.btn_zhuce_sure)
    public void onBtnZhuceSureClicked() {
        if (0 == param) {
            smsPresenter.checkValidate();
        }else if (1==param){
            smsPresenter.zhuce();
        }
    }


    public static void startIntent(Context mContext, int param) {
        Intent intent = new Intent();
        intent.putExtra("param", param);
        intent.setClass(mContext, RegisterActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public String getUserphone() {
        return zhuceEditNumber.getText().toString();
    }

    @Override
    public String getValidateCode() {
        return zhuceEditYanzheng.getText().toString();
    }

    @Override
    public String getSendValidateCode() {
        return sendMsg;
    }

    @Override
    public String getPassword() {
        return zhuceEditPassword.getText().toString();
    }

    @Override
    public void checkValidateSuccess(String sendMsg) {
        this.sendMsg = sendMsg;
    }

    @Override
    public void failed(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(RegisterActivity.this, msg);
            }
        });
    }

    @Override
    public void buttonChange(Boolean clickAble, String showText) {
        btnZhuceYzm.setClickable(clickAble);
        btnZhuceYzm.setText(showText);
    }

    @Override
    public void resetSuccess() {
        ForgotpwdDialog.startIntent(RegisterActivity.this, param);
    }

    @Override
    public void registerSuccess() {
        ForgotpwdDialog.startIntent(RegisterActivity.this, param);
    }
}
