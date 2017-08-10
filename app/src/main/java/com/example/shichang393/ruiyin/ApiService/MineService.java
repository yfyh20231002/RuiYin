package com.example.shichang393.ruiyin.ApiService;

import com.example.shichang393.ruiyin.Bean.LoginBean;
import com.example.shichang393.ruiyin.Bean.LoginPostBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Mr.zhang on 2017/8/10.
 */

public interface MineService {

//    实盘登录接口
    @POST("loginView/firmOfferLogin.do")
    Call<LoginBean> login(@Body LoginPostBean loginPostBean);
}
