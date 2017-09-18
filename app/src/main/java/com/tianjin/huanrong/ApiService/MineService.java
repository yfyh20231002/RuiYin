package com.tianjin.huanrong.ApiService;


import com.tianjin.huanrong.Bean.LoginBean;
import com.tianjin.huanrong.Bean.LoginPostBean;
import com.tianjin.huanrong.Bean.mine.AfteRegisterPostBean;
import com.tianjin.huanrong.Bean.mine.ForgotPwdPostBean;
import com.tianjin.huanrong.Bean.mine.NickNameBean;
import com.tianjin.huanrong.Bean.mine.NickNamePostBean;
import com.tianjin.huanrong.Bean.mine.UseRegisterPostBean;

import okhttp3.RequestBody;
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

    //    忘记密码接口
    @POST("loginView/forgotPwd.do")
    Call<LoginBean> forgotPwd(@Body ForgotPwdPostBean forgotPwdPostBean);

    //    注册接口
    @POST("loginView/guestLogin.do")
    Call<LoginBean> guestLogin(@Body UseRegisterPostBean useRegisterPostBean);
    //    注册后(游客)登录接口
    @POST("loginView/guestLoginNew.do")
    Call<LoginBean> guestLoginNew(@Body AfteRegisterPostBean afteRegisterPostBean);

    //    更改昵称接口
    @POST("loginView/updateCustomInfo.do")
    Call<NickNameBean> updateCustomInfo(@Body NickNamePostBean nickNamePostBean);


    /**
     * 修改头像接口
     * http://zb.006006.cn/WoDeCaiShen/updateHeadImgByUserIdForApp.do
     * @return
     */
    @POST("WoDeCaiShen/updateHeadImgByUserIdForApp.do")
    Call<NickNameBean> updateHeadImg(@Body RequestBody num);


}
