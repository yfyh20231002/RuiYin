package com.example.shichang393.ruiyin.model;

import com.example.shichang393.ruiyin.ApiService.CCTVService;
import com.example.shichang393.ruiyin.Bean.CCTVBean;
import com.example.shichang393.ruiyin.listener.OnCCTVListener;
import com.example.shichang393.ruiyin.utils.ConstanceValue;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public class CCTVModel {
    OnCCTVListener listener;
    public void setListener(OnCCTVListener listener){
        this.listener=listener;
    }

    public  void getData(String videotype){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstanceValue.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CCTVService cctvService = retrofit.create(CCTVService.class);
        Call<CCTVBean> cctv = cctvService.getCCTV(videotype);
        cctv.enqueue(new Callback<CCTVBean>() {
            @Override
            public void onResponse(Call<CCTVBean> call, Response<CCTVBean> response) {
                CCTVBean body = response.body();
                if (body==null){return;}
                List<CCTVBean.DataBean> data = body.getData();
                if (data==null){return;}
                if (data.size()>0){
                    listener.success(data);
                }else {
                    listener.failed("数据解析失败");
                }
            }

            @Override
            public void onFailure(Call<CCTVBean> call, Throwable t) {
                listener.failed("数据解析失败");
            }
        });
    }
}
