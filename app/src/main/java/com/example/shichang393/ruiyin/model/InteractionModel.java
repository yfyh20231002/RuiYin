package com.example.shichang393.ruiyin.model;

import com.example.shichang393.ruiyin.ApiService.LiveService;
import com.example.shichang393.ruiyin.Bean.ChatPBean;
import com.example.shichang393.ruiyin.Bean.ChatPostBean;
import com.example.shichang393.ruiyin.Bean.SendMessageBean;
import com.example.shichang393.ruiyin.listener.OnInteractionListener;
import com.example.shichang393.ruiyin.utils.CommonUtil;
import com.example.shichang393.ruiyin.utils.ConstanceValue;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Mr.zhang on 2017/8/1.
 */

public class InteractionModel {

    OnInteractionListener listener;

    public InteractionModel(OnInteractionListener listener) {
        this.listener = listener;
    }

    /**
     * 聊天记录筛选
     *
     * @param userId
     * @param liveId
     * @param leibie
     */
    public final void chat(String userId,  String liveId,int permission, int leibie, int chatPage, int chatCount) {
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.bannerurl);
//        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.testurl);
        LiveService liveService = retrofit.create(LiveService.class);
        Call<ChatPBean> responseBodyCall = liveService.postChat(new ChatPostBean(userId,  liveId,permission, leibie, chatPage, chatCount));
        responseBodyCall.enqueue(new Callback<ChatPBean>() {
            @Override
            public void onResponse(Call<ChatPBean> call, Response<ChatPBean> response) {
                ChatPBean body = response.body();
                if (body==null){return;}
                List<ChatPBean.DataBean.ChatBean> chat = body.getData().getChat();
                if (chat==null){return;}
                if (chat.size()==0){
                    listener.onChatFailed("暂无聊天记录");
                    listener.onChatSuccess(chat);
                }else if (chat.size()>0){
                    listener.onChatSuccess(chat);
                }
            }

            @Override
            public void onFailure(Call<ChatPBean> call, Throwable t) {
                listener.onChatFailed("数据解析失败");
            }
        });

    }


    public final void sendMessage(String userId,  String liveId,String userIcon,String userName,int userMark,int permission, int leibie,String tyonghuid,String tyonghunicheng,String chatContent,String tyonghutouxiang,String relation  ){
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.bannerurl);
        LiveService liveService = retrofit.create(LiveService.class);
        Call<ResponseBody> responseBodyCall = liveService.postSendMessage(new SendMessageBean("message",userId,CommonUtil.getUUID(),liveId,userIcon,userName,userMark,permission,leibie,tyonghuid,tyonghunicheng,chatContent,1 == permission ? 0 : 1,"1",tyonghutouxiang,relation));
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onChatFailed(t.toString());
            }
        });
    }
}
