package com.unitesoft.huanrong.model;


import com.unitesoft.huanrong.ApiService.FlashService;
import com.unitesoft.huanrong.Bean.FlashBean;
import com.unitesoft.huanrong.Bean.FlashPostData;
import com.unitesoft.huanrong.listener.OnFlashListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.zhang on 2017/6/23.
 * 财经快讯
 */

public class FlashModel {
    OnFlashListener listener;
    public FlashModel(OnFlashListener onFlashListener) {
        this.listener = onFlashListener;
    }

    /**
     * http://zb.006006.cn:10001/appsv/financialNewsView/getData.do
     *
     * @param dataType
     */
    public  void getData(final String dataType){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://zb.006006.cn:10001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FlashService flashService = retrofit.create(FlashService.class);
        Call<FlashBean> body = flashService.getBody(new FlashPostData(dataType) );
        body.enqueue(new Callback<FlashBean>() {
            @Override
            public void onResponse(Call<FlashBean> call, Response<FlashBean> response) {
                if (response.body()==null){return;}
                if (0==response.body().getSyscode()){
                   if (500==response.body().getData().getBusinesscode()){
                       List<FlashBean.DataBean.DangtianshujuBean> dangtianshuju = response.body().getData().getDangtianshuju();
                       if (dangtianshuju==null){return;}
                       if (dangtianshuju.size()>0) {
                           listener.success(dangtianshuju);
                       }else {
                           listener.failed("获取数据失败");
                       }
                   }
                }
            }

            @Override
            public void onFailure(Call<FlashBean> call, Throwable t) {
                    listener.failed("获取数据失败");
            }
        });
    }
}
