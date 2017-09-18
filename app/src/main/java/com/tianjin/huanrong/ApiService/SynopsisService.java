package com.tianjin.huanrong.ApiService;


import com.tianjin.huanrong.Bean.StrategyPostData;
import com.tianjin.huanrong.Bean.SynopsisBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Mr.zhang on 2017/7/21.
 * 简介
 */

public interface SynopsisService {
    @POST("live/selectDetail.do")
    Call<SynopsisBean> postSynopsis(@Body StrategyPostData data);
}
