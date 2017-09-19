package com.unitesoft.huanrong.ApiService;


import com.unitesoft.huanrong.Bean.IptMsgBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mr.zhang on 2017/6/27.
 * 重要消息
 */

public interface IptMsgService {

    @GET("Home/GetNewArrey")
    Call<List<IptMsgBean>> getId(@Query("ids") String id, @Query("pageindex") int dex, @Query("pagesize") int size);
}
