package com.tianjin.huanrong.ApiService;


import com.tianjin.huanrong.Bean.TokenBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * Created by Mr.zhang on 2017/7/6.
 */

public interface TokenService {

    @POST("user/getToken.json")
    @FormUrlEncoded
    Call<TokenBean> getToken(@HeaderMap Map<String, String> map, @FieldMap Map<String, String> field);
}
