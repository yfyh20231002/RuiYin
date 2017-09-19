package com.unitesoft.huanrong.ApiService;

import com.unitesoft.huanrong.Bean.StrategyBean;
import com.unitesoft.huanrong.Bean.StrategyPostData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Mr.zhang on 2017/7/18.
 * 策略
 */

public interface StrategyService {
    @POST("appsv/proposalView/proposalByZhiboshiid.do")
    Call<StrategyBean> poststrategy(@Body StrategyPostData data);
}
