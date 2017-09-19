package com.unitesoft.huanrong.ApiService;


import com.unitesoft.huanrong.Bean.CCTVBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public interface CCTVService {
    /**
     * http://appinterface.yun066.com/home/getVideoPage?videotype=%E9%B2%B8%E9%B1%BCAPP%E8%A7%86%E9%A2%91
     */

    @GET("home/getVideoPage")
    Call<CCTVBean> getCCTV(@Query("videotype") String type);
}
