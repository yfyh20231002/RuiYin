package com.example.shichang393.ruiyin.widget.activity.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shichang393.ruiyin.ApiService.MineService;
import com.example.shichang393.ruiyin.Bean.mine.NickNameBean;
import com.example.shichang393.ruiyin.Bean.mine.NickNamePostBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.manager.SharedPreferencesMgr;
import com.example.shichang393.ruiyin.utils.CommonUtil;
import com.example.shichang393.ruiyin.utils.ConstanceValue;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.widget.activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 更改昵称
 */
public class UpNickNameActivity extends Activity {

    @InjectView(R.id.cancle)
    TextView cancle;
    @InjectView(R.id.complete)
    TextView complete;
    @InjectView(R.id.nickname)
    EditText nickname;
    @InjectView(R.id.clear)
    ImageView clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upnickname);
        ButterKnife.inject(this);
        nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)){
                    clear.setVisibility(View.GONE);
                }else {
                    clear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.cancle, R.id.complete, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancle:
                finish();
                break;
            case R.id.complete:
                String trim = nickname.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    ToastUtils.showToast(this, "昵称不能为空");
                    return;
                }
                String zhanghao = SharedPreferencesMgr.getZhanghao();
                resetName(zhanghao, trim);

                break;
            case R.id.clear:
                nickname.setText("");
                break;
        }
    }

    private void resetName(String zhanghao, String name) {
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.testurl);
        MineService mineService = retrofit.create(MineService.class);
        Call<NickNameBean> responseBodyCall = mineService.updateCustomInfo(new NickNamePostBean(zhanghao, name));
        responseBodyCall.enqueue(new Callback<NickNameBean>() {
            @Override
            public void onResponse(Call<NickNameBean> call, Response<NickNameBean> response) {
                NickNameBean body = response.body();
                if (body != null) {
                    SharedPreferencesMgr.saveUsername(body.getUsers().getYonghunicheng());
                    Intent intent=new Intent();
                    intent.putExtra("change",true);
                    intent.setClass(UpNickNameActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showToast(UpNickNameActivity.this,"数据解析失败");
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<NickNameBean> call, Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast(UpNickNameActivity.this,"数据解析失败");
                    }
                });
            }
        });
    }

    public static void startIntent(Context mContext) {
        mContext.startActivity(new Intent(mContext, UpNickNameActivity.class));
    }
}
