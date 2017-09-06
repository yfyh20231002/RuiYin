package com.unitesoft.huanrong.ApiService;


import com.unitesoft.huanrong.Bean.ChatPBean;
import com.unitesoft.huanrong.Bean.ChatPostBean;
import com.unitesoft.huanrong.Bean.LiveBean;
import com.unitesoft.huanrong.Bean.LivePostData;
import com.unitesoft.huanrong.Bean.SendMessagePostBean;
import com.unitesoft.huanrong.Bean.TeacherBean;
import com.unitesoft.huanrong.Bean.live.DeleteMessagePostBean;
import com.unitesoft.huanrong.Bean.live.InsertCaoZuoPostBean;
import com.unitesoft.huanrong.Bean.live.PinZhongPostBean;
import com.unitesoft.huanrong.Bean.live.SelectGenZongPostBean;
import com.unitesoft.huanrong.Bean.live.SendMessageBean;
import com.unitesoft.huanrong.Bean.live.ShenheMessagePostBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public interface LiveService {
    /**
     * 直播室列表（post）
     * http://182.92.83.122:10001/appsv/liveRoomListView/getNoVipLiveRooms.do
     *
     * @return
     */
    @POST("appsv/liveRoomListView/getNoVipLiveRooms.do")
    Call<LiveBean> postLive();


    /**
     * 点击直播室的时候获取分析师ID
     *
     * @param livePostData
     * @return
     */
    @POST("appsv/liveRoomListView/gotoLiveRoom.do")
    Call<TeacherBean> postTeacher(@Body LivePostData livePostData);

    /**
     * 直播室历史聊天记录
     *
     * @return
     */
    @POST("appsv/liveRoomView/filterChatHistory.do")
//    @POST("liveRoomView/filterChatHistory.do")
//    ChatPBean
    Call<ChatPBean> postChat(@Body ChatPostBean chatPostBean);

    /**
     * 发送消息
     */
    @POST("appsv/live/sendtext.do")
//    @POST("live/sendtext.do")
    Call<SendMessageBean> postSendMessage(@Body SendMessagePostBean sendMessageBean);

    /**
     *发布策略里面的品种
     * @param body
     * @return
     */
    @POST("adivce/selectWareHouseTypeByZhiBoShiId.do")
    Call<ResponseBody> postPinZhong(@Body PinZhongPostBean body);

    /**
     * 查询跟踪建议的条数
     */
    @POST("live/selectCaozuo.do")
    Call<ResponseBody> postSelectCaozuo(@Body SelectGenZongPostBean bean);


    /**
     * 点击完发布建议后调取的接口
     */
    @POST("live/insertCaoZuo.do")
    Call<ResponseBody>  postInsertCaozuo(@Body InsertCaoZuoPostBean bean);


    /**
     * 保存已审核
     *
     */
    @POST("live/shenheMessage.do")
    Call<ResponseBody>  postShenheMessage(@Body ShenheMessagePostBean bean);


    /**
     * 删除消息
     */
    @POST("live/delMessageByID.do")
    Call<ResponseBody>  postDelete(@Body DeleteMessagePostBean bean);
}
