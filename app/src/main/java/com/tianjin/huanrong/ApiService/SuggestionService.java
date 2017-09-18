package com.tianjin.huanrong.ApiService;

import com.tianjin.huanrong.Bean.SuggestionBean;
import com.tianjin.huanrong.Bean.SuggestionPostData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Mr.zhang on 2017/7/10.
 */

public interface SuggestionService {
    @POST("appsv/proposalView/proposalData.do")
   Call<SuggestionBean> postsgt(@Body SuggestionPostData data);
}
