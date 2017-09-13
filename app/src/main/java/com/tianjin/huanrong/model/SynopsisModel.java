package com.tianjin.huanrong.model;

import android.text.TextUtils;

import com.tianjin.huanrong.ApiService.SynopsisService;
import com.tianjin.huanrong.Bean.StrategyPostData;
import com.tianjin.huanrong.Bean.SynopsisBean;
import com.tianjin.huanrong.listener.OnSynopsisListener;
import com.tianjin.huanrong.utils.ConstanceValue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Mr.zhang on 2017/7/21.
 */

public class SynopsisModel {
    OnSynopsisListener listener;

    public SynopsisModel(OnSynopsisListener listener) {
        this.listener = listener;
    }

    public void postData(String zhiboshiid){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstanceValue.testurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SynopsisService synopsisService = retrofit.create(SynopsisService.class);
        Call<SynopsisBean> synopsisBeanCall = synopsisService.postSynopsis(new StrategyPostData(zhiboshiid));
        synopsisBeanCall.enqueue(new Callback<SynopsisBean>() {
            @Override
            public void onResponse(Call<SynopsisBean> call, Response<SynopsisBean> response) {
                SynopsisBean body = response.body();
                if (body==null){
                    return;
                }
                String jianjieurl = body.getJianjieurl();
                if (!TextUtils.isEmpty(jianjieurl)){
                    listener.success(ConstanceValue.testurl+jianjieurl);
                }
            }

            @Override
            public void onFailure(Call<SynopsisBean> call, Throwable t) {
                    listener.failed("数据解析失败");
            }
        });
    }
}
