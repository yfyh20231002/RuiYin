package com.example.shichang393.ruiyin.widget.fragment.live;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shichang393.ruiyin.ApiService.LiveService;
import com.example.shichang393.ruiyin.event.OnAdviseEvent;
import com.example.shichang393.ruiyin.Bean.live.SelectGenZongPostBean;
import com.example.shichang393.ruiyin.Bean.live.SelectGenzongBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.listener.live.CancleCallback;
import com.example.shichang393.ruiyin.manager.SharedPreferencesMgr;
import com.example.shichang393.ruiyin.utils.CommonUtil;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.widget.adapter.live.GenzongAdapter;

import org.greenrobot.eventbus.EventBus;
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
    Spinner tvDirection;
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

    public GenzongFragment(CancleCallback cancleCallback) {
        this.cancleCallback = cancleCallback;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
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
        Retrofit retrofit = CommonUtil.retrofit("http://192.168.1.22:8080/");
        LiveService liveService = retrofit.create(LiveService.class);
        String userid = SharedPreferencesMgr.getuserid();
        Call<ResponseBody> responseBodyCall = liveService.postSelectCaozuo(new SelectGenZongPostBean(userid));
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    JSONArray array = new JSONArray(result);
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
        for (int i = 0; i <list.size(); i++) {
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
        String s = list.get(i).getJiancangfangxiang();
        if (TextUtils.equals("做多",s)){
            tvDirection.setSelection(0);
        }else if (TextUtils.equals("做空",s)){
            tvDirection.setSelection(1);
        }else if (TextUtils.equals("震荡",s)){
            tvDirection.setSelection(2);
        }
        tvMubiao.setText(list.get(i).getCloseprice());
        tvZhisun.setText(list.get(i).getStopprice());
        tvCount.setText("共" + list.size() + "条");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.bt_yes, R.id.btn_no, R.id.btn_fabu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
        EventBus.getDefault().post(new OnAdviseEvent(2, "", tvJiancangwei.getText().toString(), tvDirection.getSelectedItem().toString(), etJiacang.getText().toString(), etMubiao.getText().toString(), etZhisun.getText().toString(), baodan));
        cancleCallback.cancle();
    }

}
