package com.tianjin.huanrong.ApiService;


import com.tianjin.huanrong.Bean.NoticeBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mr.zhang on 2017/6/26.
 * 交易公告
 */

public interface NoticeService {
    //http://appinterface.yun066.com/Home/GetNewList?id=49&pageindex=1&pagesize=1000

    @GET("Home/GetNewList")
    Call<List<NoticeBean>> getId(@Query("id") int id, @Query("pageindex") int dex, @Query("pagesize") int size);
}
