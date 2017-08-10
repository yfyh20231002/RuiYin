package com.example.shichang393.ruiyin.widget.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.shichang393.ruiyin.Bean.LoginBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.presenter.LoginPresenter;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.view.mine.LoginView;
import com.example.shichang393.ruiyin.widget.activity.MainActivity;
import com.example.shichang393.ruiyin.widget.view.sweetdialog.SweetAlertDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @InjectView(R.id.btn_login_exit)
    ImageButton btnLoginExit;
    @InjectView(R.id.login_edit_number)
    EditText loginEditNumber;
    @InjectView(R.id.login_edit_password)
    EditText loginEditPassword;
    @InjectView(R.id.login_text_tishi)
    TextView loginTextTishi;
    @InjectView(R.id.btn_login_denglu)
    Button btnLoginDenglu;
    @InjectView(R.id.btn_login_rem)
    Button btnLoginRem;
    @InjectView(R.id.btn_login_zhuce)
    Button btnLoginZhuce;
    LoginPresenter loginPresenter;
    private SweetAlertDialog sweetAlertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_login_exit, R.id.btn_login_denglu, R.id.btn_login_rem, R.id.btn_login_zhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            退出
            case R.id.btn_login_exit:
                finish();
                break;
//            登录
            case R.id.btn_login_denglu:
                showProgress();
                loginPresenter=new LoginPresenter(this);
                loginPresenter.login();
                break;
//            忘记密码
            case R.id.btn_login_rem:
                RegisterActivity.startIntent(LoginActivity.this, 0);
                break;
//            注册
            case R.id.btn_login_zhuce:
                RegisterActivity.startIntent(LoginActivity.this, 1);
                break;
        }
    }

    public static void startIntent(Context mContext) {
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }

    @Override
    public String zhanghao() {
        String zhanghao = loginEditNumber.getText().toString().trim();
        return zhanghao;
    }

    @Override
    public String mima() {
        String mima = loginEditPassword.getText().toString().trim();
        return mima;
    }

    @Override
    public void loginsuccess(LoginBean.DataBean dataBean) {
        int code = dataBean.getBusinesscode();
        if (103==code){
            sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
            sweetAlertDialog.setTitleText("登录成功");
            sweetAlertDialog.showConfirmButton(false);
            sweetAlertDialog.setDialogCloseListener(new SweetAlertDialog.DialogCloseListener() {
                @Override
                public void dialogClose() {
                    dismissProgress();
                    Intent intent=new Intent();
                    intent.putExtra("change",true);
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        } else if (104 == code) {
            dismissProgress();
            ToastUtils.showToast(LoginActivity.this, "密码错误");
        } else if (102 == code) {
            dismissProgress();
            loginTextTishi.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loginfailed(String msg) {
        dismissProgress();
        ToastUtils.showToast(LoginActivity.this, msg);
    }

    @Override
    public void showProgress() {
        if (null == sweetAlertDialog) {
            sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        } else {
            sweetAlertDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        }
        sweetAlertDialog.setTitleText("登录中...");
        sweetAlertDialog.show();
    }
   private  void dismissProgress() {
        if (null != sweetAlertDialog && sweetAlertDialog.isShowing()) {
            sweetAlertDialog.dismiss();
        }
    }
}