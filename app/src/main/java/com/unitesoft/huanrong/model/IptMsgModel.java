package com.unitesoft.huanrong.model;


import com.unitesoft.huanrong.ApiService.IptMsgService;
import com.unitesoft.huanrong.Bean.IptMsgBean;
import com.unitesoft.huanrong.listener.OnIptMsgListener;
import com.unitesoft.huanrong.utils.ConstanceValue;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.zhang on 2017/6/27.
 * 重点消息
 */

public class IptMsgModel {
    OnIptMsgListener listener;

    public IptMsgModel(OnIptMsgListener listener) {
        this.listener = listener;
    }

    public void getData(String id,int index, int size){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstanceValue.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IptMsgService iptMsgService = retrofit.create(IptMsgService.class);
        Call<List<IptMsgBean>> call = iptMsgService.getId(id, index, size);
        call.enqueue(new Callback<List<IptMsgBean>>() {
            @Override
            public void onResponse(Call<List<IptMsgBean>> call, Response<List<IptMsgBean>> response) {
                List<IptMsgBean> list=response.body();
                if (list==null){
                    return;
                }
                if (list.size()>0){
                    listener.success(list);
                }else {
                    listener.faild("获取数据失败");
                }
            }

            @Override
            public void onFailure(Call<List<IptMsgBean>> call, Throwable t) {
                listener.faild("获取数据失败");
            }
        });
    }
}
