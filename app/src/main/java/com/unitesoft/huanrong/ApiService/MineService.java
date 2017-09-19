package com.unitesoft.huanrong.ApiService;


import com.unitesoft.huanrong.Bean.LoginBean;
import com.unitesoft.huanrong.Bean.LoginPostBean;
import com.unitesoft.huanrong.Bean.mine.AfteRegisterPostBean;
import com.unitesoft.huanrong.Bean.mine.ForgotPwdPostBean;
import com.unitesoft.huanrong.Bean.mine.NickNameBean;
import com.unitesoft.huanrong.Bean.mine.NickNamePostBean;
import com.unitesoft.huanrong.Bean.mine.UseRegisterPostBean;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
     *
     * @return
     */
    @POST("WoDeCaiShen/updateHeadImgByUserIdForApp.do")
    Call<NickNameBean> updateHeadImg(@Body RequestBody num);


    /**
     * 发送短信验证码
     *
     * @return
     */
    @POST("ImageVerification/ValidateCodeNoImg")
    Call<ResponseBody> sendSMS(@Query("phone") String phone, @Query("bm") String bm, @Query("source") String source);


}
