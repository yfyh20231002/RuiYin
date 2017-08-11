package com.example.shichang393.ruiyin.widget.activity.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.widget.activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ForgotpwdDialog extends Activity {

    @InjectView(R.id.image)
    ImageView image;
    @InjectView(R.id.text)
    TextView text;
    @InjectView(R.id.confirm)
    TextView confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        setContentView(R.layout.forgotpwd_dialog);
        ButterKnife.inject(this);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int wid = metrics.widthPixels;
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (wid * 0.8);
        getWindow().setAttributes(params);
/**
 * param=0代表重置密码成功
 * param=1代表注册成功
 */
        int param = getIntent().getIntExtra("param", 0);
        if (0 == param) {
            image.setImageResource(R.mipmap.login_mima_succeed);
            text.setText("密码重置成功");
        } else {
            image.setImageResource(R.mipmap.login_zhuce_succeed);
            text.setText("恭喜您注册成功！");
        }

    }

    @OnClick(R.id.confirm)
    public void onViewClicked() {
        startActivity(new Intent(ForgotpwdDialog.this, MainActivity.class));
    }

    public static void startIntent(Context context, int param) {

        Intent intent = new Intent();
        intent.putExtra("param", param);
        intent.setClass(context, ForgotpwdDialog.class);
        context.startActivity(intent);
    }
}
