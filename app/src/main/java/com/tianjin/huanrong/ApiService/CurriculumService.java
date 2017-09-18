package com.tianjin.huanrong.ApiService;


import com.tianjin.huanrong.Bean.CurriculumBean;
import com.tianjin.huanrong.Bean.StrategyPostData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Mr.zhang on 2017/7/21.
 * 课程表
 */

public interface CurriculumService {
    //    @POST("appsv/live/selectWatch.do")
    @POST("live/selectWatch.do")
    Call<CurriculumBean> postcurriculum(@Body StrategyPostData data);
}
