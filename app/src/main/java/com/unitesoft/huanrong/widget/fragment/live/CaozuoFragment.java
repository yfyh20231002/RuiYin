package com.unitesoft.huanrong.widget.fragment.live;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.event.OnAdviseEvent;
import com.unitesoft.huanrong.listener.live.CancleCallback;
import com.unitesoft.huanrong.utils.ToastUtils;
import com.unitesoft.huanrong.widget.fragment.dialog.DirectionDialog;
import com.unitesoft.huanrong.widget.fragment.dialog.PinZhongDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Mr.zhang on 2017/8/15.
 * 操作建议
 */
public class CaozuoFragment extends BaseFragment {


    @InjectView(R.id.tv_pinzhong)
    TextView tvPinzhong;
    @InjectView(R.id.et_price)
    EditText etPrice;

    @InjectView(R.id.et_mubiao)
    EditText etMubiao;
    @InjectView(R.id.et_zhisun)
    EditText etZhisun;
    @InjectView(R.id.bt_yes)
    Button btYes;
    @InjectView(R.id.btn_no)
    Button btnNo;
    @InjectView(R.id.btn_fabu)
    Button btnFabu;
    @InjectView(R.id.tv_direction)
    TextView tvDirection;

    private CancleCallback cancleCallback;
    private Context mContext;
    private String baodan = "0";

    private View view;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    public CaozuoFragment() {
    }

    public void setCancleCallback(CancleCallback cancleCallback){
        this.cancleCallback = cancleCallback;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.caozuo_fragment, container, false);
            isPrepared = true;
            ButterKnife.inject(this, view);
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_pinzhong, R.id.tv_direction,R.id.bt_yes, R.id.btn_no, R.id.btn_fabu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_pinzhong:
                PinZhongDialog pinZhongDialog = new PinZhongDialog();
                pinZhongDialog.show(getChildFragmentManager(), "");
                break;
            case R.id.tv_direction:
                DirectionDialog directionDialog=new DirectionDialog();
                directionDialog.show(getChildFragmentManager(),"directionDialog");
                break;
            case R.id.bt_yes:
                baodan = "1";
                break;
            case R.id.btn_no:
                baodan = "0";
                break;
            case R.id.btn_fabu:
                if (TextUtils.isEmpty(etPrice.getText()) || TextUtils.isEmpty(etMubiao.getText()) || TextUtils.isEmpty(etZhisun.getText())) {
                    ToastUtils.showToast(mContext, "请将表单填写完整");
                    return;
                }
                fabu();
                break;
        }
    }


    private void fabu() {
        EventBus.getDefault().post(new OnAdviseEvent(1, tvPinzhong.getText().toString(), etPrice.getText().toString(), tvDirection.getText().toString(),
                null, etMubiao.getText().toString(), etZhisun.getText().toString(), baodan));
        cancleCallback.cancle();
    }


    @Subscribe
    public void onMessageEvent(String s) {
        if (s.equals("做多")) {
            tvDirection.setText(s);
        } else if (s.equals("做空")) {
            tvDirection.setText(s);
        } else if (s.equals("震荡")) {
            tvDirection.setText(s);
        } else {
            tvPinzhong.setText(s);
        }
    }


}
