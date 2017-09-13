package com.tianjin.huanrong.model;


import com.tianjin.huanrong.ApiService.CurriculumService;
import com.tianjin.huanrong.Bean.CurriculumBean;
import com.tianjin.huanrong.Bean.StrategyPostData;
import com.tianjin.huanrong.listener.OnCurriculumListener;
import com.tianjin.huanrong.utils.ConstanceValue;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.zhang on 2017/7/21.
 */

public class CurriculumModel {
    OnCurriculumListener listener;

    public CurriculumModel(OnCurriculumListener listener) {
        this.listener = listener;
    }

    public  void postData(String zhiboshiid){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstanceValue.testurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CurriculumService curriculumService = retrofit.create(CurriculumService.class);
        Call<CurriculumBean> postcurriculum = curriculumService.postcurriculum(new StrategyPostData(zhiboshiid));
        postcurriculum.enqueue(new Callback<CurriculumBean>() {
            @Override
            public void onResponse(Call<CurriculumBean> call, Response<CurriculumBean> response) {
                CurriculumBean body = response.body();
                if (body==null){return;}
                List<CurriculumBean.PaibanBean> paiban = body.getPaiban();
                List<CurriculumBean.YhtxBean> yhtx = body.getYhtx();
                if (paiban==null||yhtx==null){return;}
                if (paiban.size()>0&&yhtx.size()>0){
                    listener.success(paiban,yhtx);
                }else {
                    listener.failed("暂无排班");
                }
            }

            @Override
            public void onFailure(Call<CurriculumBean> call, Throwable t) {
                listener.failed("数据解析失败");
            }
        });
    }
}
