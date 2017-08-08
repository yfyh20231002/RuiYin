package com.example.shichang393.ruiyin.model;

import com.example.shichang393.ruiyin.ApiService.SuggestionService;
import com.example.shichang393.ruiyin.Bean.SuggestionBean;
import com.example.shichang393.ruiyin.Bean.SuggestionPostData;
import com.example.shichang393.ruiyin.listener.OnSuggestionListener;
import com.example.shichang393.ruiyin.utils.ConstanceValue;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.zhang on 2017/7/10.
 */

public class SuggestionModel {
    OnSuggestionListener listener;

    public SuggestionModel(OnSuggestionListener listener) {
        this.listener = listener;
    }

    public  void postData(String userId){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstanceValue.bannerurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SuggestionService suggestionService = retrofit.create(SuggestionService.class);
        Call<SuggestionBean> postsgt = suggestionService.postsgt(new SuggestionPostData(userId));
        postsgt.enqueue(new Callback<SuggestionBean>() {
            @Override
            public void onResponse(Call<SuggestionBean> call, Response<SuggestionBean> response) {
                SuggestionBean body = response.body();
                if (body==null){return;}
                List<SuggestionBean.DataBean.LIVEROOMSSTICKBean> liveroomsstick = body.getData().getLIVEROOMSSTICK();
                if (liveroomsstick==null){return;}
                if (liveroomsstick.size()>0){
                    listener.success(liveroomsstick);
                }else {
                    listener.failed("暂无建议");
                }
            }

            @Override
            public void onFailure(Call<SuggestionBean> call, Throwable t) {
                listener.failed("数据解析失败");
            }
        });
    }
}
