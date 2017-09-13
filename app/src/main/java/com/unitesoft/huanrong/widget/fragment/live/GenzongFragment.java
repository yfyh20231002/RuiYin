package com.unitesoft.huanrong.widget.fragment.live;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.unitesoft.huanrong.ApiService.LiveService;
import com.unitesoft.huanrong.Bean.live.SelectGenZongPostBean;
import com.unitesoft.huanrong.Bean.live.SelectGenzongBean;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.event.OnAdviseEvent;
import com.unitesoft.huanrong.listener.live.CancleCallback;
import com.unitesoft.huanrong.manager.SharedPreferencesMgr;
import com.unitesoft.huanrong.utils.CommonUtil;
import com.unitesoft.huanrong.utils.ConstanceValue;
import com.unitesoft.huanrong.utils.ToastUtils;
import com.unitesoft.huanrong.widget.adapter.live.GenzongAdapter;
import com.unitesoft.huanrong.widget.fragment.dialog.DirectionDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Mr.zhang on 2017/8/15.
 */

public class GenzongFragment extends BaseFragment {


    @InjectView(R.id.tv_pinzhong)
    TextView tvPinzhong;
    @InjectView(R.id.tv_direction)
    TextView tvDirection;
    @InjectView(R.id.tv_jiancangwei)
    TextView tvJiancangwei;
    @InjectView(R.id.tv_shijia)
    TextView tvShijia;
    @InjectView(R.id.et_jiacang)
    EditText etJiacang;
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
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.tv_count)
    TextView tvCount;
    @InjectView(R.id.tv_mubiao)
    TextView tvMubiao;
    @InjectView(R.id.tv_zhisun)
    TextView tvZhisun;


    private Context mContext;
    private CancleCallback cancleCallback;
    private GenzongAdapter adapter;
    private View view;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    private String baodan = "0";
    private List<SelectGenzongBean> mlist = new ArrayList<>();
    private List<String> count = new ArrayList<>();
    private String s = "做多";

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
            view = inflater.inflate(R.layout.genzong_fragment, container, false);
            isPrepared = true;
            ButterKnife.inject(this, view);
            init();
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        ButterKnife.inject(this, view);
        return view;
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        requestRetrofit();
    }

    private void requestRetrofit() {
//        "http://192.168.1.22:8080/"
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.baseImage);
        LiveService liveService = retrofit.create(LiveService.class);
        String username = SharedPreferencesMgr.getUsername();
        Call<ResponseBody> responseBodyCall = liveService.postSelectCaozuo(new SelectGenZongPostBean(username));
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    if (TextUtils.isEmpty(result)) {
                        ToastUtils.showToast(mContext, "暂无跟踪建议");
                        return;
                    }
                    JSONArray array = new JSONArray(result);
                    /*"tradingid":"00F246765F2B48ACB0918B6453DAA4AF",
                            "delflg":"0",
                            "fenxishixingming":"李亚蓓",
                            "variety":"柏油",
                            "jiancangfangxiang":"做空",
                            "openprice":"2388--2400",
                            "stopprice":"2425",
                            "surplusprice":"",
                            "closeprice":"2320-2300",
                            "createdate":1503372612000,
                            "updatedate":null,
                            "ifzhudan":0,
                            "jianyileixing":0,
                            "fenxishitouxiang":"/upload/kehuziliaoguanli/201601/1452567336927.png"*/
                    if (array.length() > 0) {
                        mlist.clear();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            String variety = jsonObject.getString("variety");
                            String fangxiang = jsonObject.getString("jiancangfangxiang");
                            String price = jsonObject.getString("openprice");
                            String mubiao = jsonObject.getString("closeprice");
                            String zhisun = jsonObject.getString("stopprice");
                            int baodan = jsonObject.getInt("ifzhudan");
                            mlist.add(new SelectGenzongBean(variety, fangxiang, price, mubiao, zhisun, baodan));
                        }
                        data(mlist);

                    } else {
                        ToastUtils.showToast(mContext, "暂无建议");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ToastUtils.showToast(mContext, "解析错误");
            }
        });
    }

    private void data(final List<SelectGenzongBean> list) {
        qianchong(0, list);
        count.clear();
        for (int i = 0; i < list.size(); i++) {
            count.add(i + 1 + "");
        }
        if (adapter == null) {
            adapter = new GenzongAdapter(count);
            recyclerview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnItemClickListener(new GenzongAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                qianchong(position, list);
            }
        });

    }

    private void qianchong(int i, List<SelectGenzongBean> list) {
        tvPinzhong.setText(list.get(i).getVariety());
        tvJiancangwei.setText(list.get(i).getOpenprice());
        s = list.get(i).getJiancangfangxiang();
        tvDirection.setText(s);
        tvMubiao.setText(list.get(i).getCloseprice());
        tvZhisun.setText(list.get(i).getStopprice());
        tvCount.setText("共" + list.size() + "条");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.reset(this);
    }


    @OnClick({R.id.tv_direction,R.id.bt_yes, R.id.btn_no, R.id.btn_fabu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                fabu();

                break;
        }
    }

    private void fabu() {
        if (TextUtils.isEmpty(tvJiancangwei.getText()) || TextUtils.isEmpty(etJiacang.getText()) || TextUtils.isEmpty(etMubiao.getText()) || TextUtils.isEmpty(etZhisun.getText())) {
            ToastUtils.showToast(mContext, "请将表单填写完整");
            return;
        }
        EventBus.getDefault().post(new OnAdviseEvent(2, "", tvJiancangwei.getText().toString(), tvDirection.getText().toString(), etJiacang.getText().toString(), etMubiao.getText().toString(), etZhisun.getText().toString(), baodan));
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
        }
    }

}
