package com.tianjin.huanrong.widget.fragment.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.tianjin.huanrong.ApiService.LiveService;
import com.tianjin.huanrong.Bean.live.PinZhongPostBean;
import com.tianjin.huanrong.R;
import com.tianjin.huanrong.manager.SharedPreferencesMgr;
import com.tianjin.huanrong.utils.CommonUtil;
import com.tianjin.huanrong.utils.ConstanceValue;
import com.tianjin.huanrong.utils.ToastUtils;
import com.tianjin.huanrong.widget.adapter.live.PinZhongAdapter;
import com.tianjin.huanrong.widget.view.DividerItemDecoration;

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
 * Created by Mr.zhang on 2017/8/16.
 */

public class PinZhongDialog extends DialogFragment {
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    @InjectView(R.id.cancle)
    TextView cancle;
    private List<String> mlist = new ArrayList<>();
    private Context mContext;
    private PinZhongAdapter adapter;

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
        View view = inflater.inflate(R.layout.pinzhong, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.testurl);
        LiveService liveService = retrofit.create(LiveService.class);
        String liveid = SharedPreferencesMgr.getZhiboshiid();
        Call<ResponseBody> responseBodyCall = liveService.postPinZhong(new PinZhongPostBean(liveid));
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                /**
                 * zhiboshiid : FE7C1D82811A4E7DB4BA84D77E141F0A
                 * warehousetypeid : 17785358A55E454BB6F55A7E6F02D4CF
                 * warehousetypename : 咖啡
                 */
                try {
                    String result = response.body().string();
                    if (result != null) {
                        JSONArray array = new JSONArray(result);
                        if (array.length() > 0) {
                            for (int i = 0; i < array.length(); i++) {
                                String string = array.getString(i);
                                JSONObject object = new JSONObject(string);
                                String warehousetypename = object.getString("warehousetypename");
                                mlist.add(warehousetypename);
                            }
                            data(mlist);
                        } else {
                            ToastUtils.showToast(mContext, "暂无建仓品种");
                        }
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

    private void data(final List<String> list) {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerview.setLayoutManager(manager);
        recyclerview.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        if (adapter == null) {
            adapter = new PinZhongAdapter(list);
            recyclerview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setItemClickLitener(new PinZhongAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                EventBus.getDefault().post(list.get(position));
                dismiss();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.cancle)
    public void onViewClicked() {
        dismiss();
    }
}
