package com.tianjin.huanrong.model;


import com.tianjin.huanrong.ApiService.LiveService;
import com.tianjin.huanrong.Bean.ChatPBean;
import com.tianjin.huanrong.Bean.ChatPostBean;
import com.tianjin.huanrong.Bean.SendMessagePostBean;
import com.tianjin.huanrong.Bean.live.DeleteMessagePostBean;
import com.tianjin.huanrong.Bean.live.SendMessageBean;
import com.tianjin.huanrong.Bean.live.ShenheMessagePostBean;
import com.tianjin.huanrong.listener.OnInteractionListener;
import com.tianjin.huanrong.manager.SharedPreferencesMgr;
import com.tianjin.huanrong.utils.CommonUtil;
import com.tianjin.huanrong.utils.ConstanceValue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
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
    public final void chat(String userId, String liveId, int permission, int leibie, int chatPage, int chatCount) {
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.bannerurl);
//        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.testurl);
        LiveService liveService = retrofit.create(LiveService.class);
        Call<ChatPBean> responseBodyCall = liveService.postChat(new ChatPostBean(userId, liveId, permission, leibie, chatPage, chatCount));
        responseBodyCall.enqueue(new Callback<ChatPBean>() {
            @Override
            public void onResponse(Call<ChatPBean> call, Response<ChatPBean> response) {
                ChatPBean body = response.body();
                if (body == null) {
                    return;
                }
                int businesscode = body.getData().getBusinesscode();
                if (305 == businesscode) {
                    listener.tanchuang();
                } else if (300 == businesscode) {
                    List<ChatPBean.DataBean.ChatBean> chat = body.getData().getChat();
                    if (chat == null) {
                        return;
                    }
                    if (chat.size() == 0) {
                        listener.onChatFailed("暂无聊天记录");
                        listener.onChatSuccess(chat);
                    } else if (chat.size() > 0) {
                        listener.onChatSuccess(chat);
                    }
                }
            }

            @Override
            public void onFailure(Call<ChatPBean> call, Throwable t) {
                listener.onChatFailed("数据解析失败");
            }
        });
    }


    public final void sendMessage(String userId, String liveId, String userIcon, String userName, int userMark, int permission, int leibie, String tyonghuid, String tyonghunicheng, String chatContent, String tyonghutouxiang, final String relation) {
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.bannerurl);
        LiveService liveService = retrofit.create(LiveService.class);
        Call<SendMessageBean> responseBodyCall = liveService.postSendMessage(new SendMessagePostBean("message", userId, CommonUtil.getUUID(), liveId, userIcon, userName, userMark, permission, leibie, tyonghuid, tyonghunicheng, chatContent, 1 == permission ? 0 : 1, "1", tyonghutouxiang, relation));
        responseBodyCall.enqueue(new Callback<SendMessageBean>() {
            @Override
            public void onResponse(Call<SendMessageBean> call, Response<SendMessageBean> response) {
                SendMessageBean body = response.body();
                if (body != null) {
                    String msg = body.getMsg();
                    HashMap<String, String> jsonObjmap = new HashMap<String, String>();

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(msg);
                        Iterator it = jsonObject.keys();
                        while (it.hasNext()) {
                            String key = String.valueOf(it.next());
                            String value = jsonObject.get(key) == null ? "" : jsonObject.get(key).toString();
                            jsonObjmap.put(key, value);
                        }
                        String messageid = jsonObjmap.get("messageid");
                        SharedPreferencesMgr.saveMessageid(messageid);
                        listener.onSendSuccess();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SendMessageBean> call, Throwable t) {
                listener.onChatFailed(t.toString());
            }
        });
    }


    /**
     * 保存已审核
     *
     * @param msgId 消息id
     */
    public void saveCheck(String msgId, String userId) {
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.testurl);
        LiveService liveService = retrofit.create(LiveService.class);
        Call<ResponseBody> responseBodyCall = liveService.postShenheMessage(new ShenheMessagePostBean(msgId, userId));
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


    /**
     * 删除消息
     */
    public void deleteMessage(ChatPBean.DataBean.ChatBean chatBean) {
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.testurl);
        LiveService liveService = retrofit.create(LiveService.class);
        Call<ResponseBody> responseBodyCall = liveService.postDelete(new DeleteMessagePostBean(chatBean.getMessageid(), chatBean.getTozhiboshiid(), "1", "shanchu", SharedPreferencesMgr.getuserid()));
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
