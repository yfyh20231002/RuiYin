package com.example.shichang393.ruiyin.model;

import com.example.shichang393.ruiyin.ApiService.NoticeService;
import com.example.shichang393.ruiyin.Bean.NoticeBean;
import com.example.shichang393.ruiyin.listener.OnNoticeListener;
import com.example.shichang393.ruiyin.utils.ConstanceValue;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.zhang on 2017/6/26.
 */

public class NoticeModel {
    OnNoticeListener listener;

    public NoticeModel(OnNoticeListener listener) {
        this.listener = listener;
    }

    //http://appinterface.yun066.com/Home/GetNewList?id=49&pageindex=1&pagesize=1000
    public  void getData(int id,int index, int size){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstanceValue.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NoticeService service=retrofit.create(NoticeService.class);
        Call<List<NoticeBean>> call=service.getId(id,index,size);
        call.enqueue(new Callback<List<NoticeBean>>() {
            @Override
            public void onResponse(Call<List<NoticeBean>> call, Response<List<NoticeBean>> response) {
                List<NoticeBean> list=response.body();
                if (list==null){return;}
                if (list.size()>0) {
                    listener.success(list);
                }else {
                    listener.failed("获取数据失败");
                }
            }

            @Override
            public void onFailure(Call<List<NoticeBean>> call, Throwable t) {
                listener.failed("获取数据失败");
            }
        });
    }
}
