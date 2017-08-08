package com.example.shichang393.ruiyin.ApiService;

import com.example.shichang393.ruiyin.Bean.ChatPBean;
import com.example.shichang393.ruiyin.Bean.ChatPostBean;
import com.example.shichang393.ruiyin.Bean.LiveBean;
import com.example.shichang393.ruiyin.Bean.LivePostData;
import com.example.shichang393.ruiyin.Bean.SendMessageBean;
import com.example.shichang393.ruiyin.Bean.TeacherBean;

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
    Call<ChatPBean> postChat(@Body ChatPostBean chatPostBean);

    /**
     * 发送消息
     */
//    @POST("appsv/live/sendtext.do")
    @POST("live/sendtext.do")
    Call<ResponseBody> postSendMessage(@Body SendMessageBean sendMessageBean);
}
