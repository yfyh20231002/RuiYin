package com.unitesoft.huanrong.widget.fragment.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.unitesoft.huanrong.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Mr.zhang on 2017/9/12.
 * 建仓方向
 */

public class DirectionDialog extends DialogFragment {

    @InjectView(R.id.zuoduo)
    TextView zuoduo;
    @InjectView(R.id.zuokong)
    TextView zuokong;
    @InjectView(R.id.zhendang)
    TextView zhendang;
    @InjectView(R.id.cancle)
    TextView cancle;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams mLayoutParams = getDialog().getWindow().getAttributes();
        mLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(mLayoutParams);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 设置底部显示
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        // 设置背景透明
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // 去掉标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // set cancel on touch outside
        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.direction, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.zuoduo, R.id.zuokong, R.id.zhendang, R.id.cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zuoduo:
                EventBus.getDefault().post("做多");
                break;
            case R.id.zuokong:
                EventBus.getDefault().post("做空");
                break;
            case R.id.zhendang:
                EventBus.getDefault().post("震荡");
                break;
            case R.id.cancle:
                break;
        }
        dismiss();
    }
}
