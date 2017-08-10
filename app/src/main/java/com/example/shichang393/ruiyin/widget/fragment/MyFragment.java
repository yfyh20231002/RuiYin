package com.example.shichang393.ruiyin.widget.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.manager.SharedPreferencesMgr;
import com.example.shichang393.ruiyin.utils.ConstanceValue;
import com.example.shichang393.ruiyin.widget.activity.mine.LoginActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class MyFragment extends Fragment {


    @InjectView(R.id.imag_my_touxiang)
    CircleImageView imagMyTouxiang;
    @InjectView(R.id.bt_login)
    Button btLogin;
    @InjectView(R.id.tex_my_nicheng)
    TextView texMyNicheng;
    @InjectView(R.id.btn_my_nicheng)
    LinearLayout btnMyNicheng;
    @InjectView(R.id.btn_my_touxiang)
    LinearLayout btnMyTouxiang;
    @InjectView(R.id.btn_my_mima)
    LinearLayout btnMyMima;
    @InjectView(R.id.btn_my_zhanghao)
    LinearLayout btnMyZhanghao;
    @InjectView(R.id.btn_my_tixing)
    LinearLayout btnMyTixing;
    @InjectView(R.id.my_imag_shezhi)
    ImageView myImagShezhi;
    @InjectView(R.id.checkbtn_set_on)
    ImageButton checkbtnSetOn;
    @InjectView(R.id.checkbtn_set_off)
    ImageButton checkbtnSetOff;
    @InjectView(R.id.btn_my_shezhi)
    RelativeLayout btnMyShezhi;
    @InjectView(R.id.btn_my_haoping)
    LinearLayout btnMyHaoping;
    @InjectView(R.id.textView2)
    TextView textView2;
    @InjectView(R.id.btn_my_share)
    LinearLayout btnMyShare;
    @InjectView(R.id.btn_my_aboutus)
    LinearLayout btnMyAboutus;
    private Context     mContext;
    private Activity activity;
    private String Iconurl,name;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext=activity;
        this.activity=activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Iconurl= SharedPreferencesMgr.getUserIcon();
        name=SharedPreferencesMgr.getUsername();
        if (!TextUtils.isEmpty(Iconurl)){
            Glide.with(mContext).load(ConstanceValue.baseImage+"/"+Iconurl).into(imagMyTouxiang);
        }
        if (!TextUtils.isEmpty(name)){
            btLogin.setVisibility(View.GONE);
            texMyNicheng.setVisibility(View.VISIBLE);
            texMyNicheng.setText(name);
        }else {
            btLogin.setVisibility(View.VISIBLE);
            texMyNicheng.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.bt_login, R.id.btn_my_nicheng, R.id.btn_my_touxiang, R.id.btn_my_mima, R.id.btn_my_zhanghao, R.id.btn_my_tixing, R.id.checkbtn_set_on, R.id.checkbtn_set_off, R.id.btn_my_haoping, R.id.btn_my_share, R.id.btn_my_aboutus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                LoginActivity.startIntent(mContext);
                break;
//            更改昵称
            case R.id.btn_my_nicheng:
                break;
//            修改头像
            case R.id.btn_my_touxiang:
                break;
//            修改密码
            case R.id.btn_my_mima:
                break;
//            退出账号
            case R.id.btn_my_zhanghao:
                exit();
                break;
            case R.id.btn_my_tixing:
                break;
            case R.id.checkbtn_set_on:
                checkbtnSetOn.setVisibility(View.VISIBLE);
                checkbtnSetOff.setVisibility(View.GONE);
                break;
            case R.id.checkbtn_set_off:
                checkbtnSetOn.setVisibility(View.GONE);
                checkbtnSetOff.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_my_haoping:
                break;
            case R.id.btn_my_share:
                break;
            case R.id.btn_my_aboutus:
                break;
        }
    }

    private void exit() {

        View view=LayoutInflater.from(mContext).inflate(R.layout.popup_bottom,null);
        final PopupWindow popupWindow=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //显示PopupWindow
        View rootview = LayoutInflater.from(mContext).inflate(R.layout.fragment_my, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        Button queding= (Button) view.findViewById(R.id.btn_sure);
        Button cancel= (Button) view.findViewById(R.id.btn_cancel);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
