package com.unitesoft.huanrong.presenter;

import com.unitesoft.huanrong.Bean.ChatPBean;
import com.unitesoft.huanrong.listener.OnInteractionListener;
import com.unitesoft.huanrong.model.InteractionModel;
import com.unitesoft.huanrong.view.InteractionView;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/8/1.
 */

public class InteractionPresenter implements OnInteractionListener {
    InteractionView view;
    InteractionModel model;

    public InteractionPresenter(InteractionView view) {
        this.view = view;
        model=new InteractionModel(this);
    }
    public void postChatData(String userId,  String liveId,int permission, int type, int chatPage, int chatCount){
        model.chat(userId,liveId,permission,type,chatPage,chatCount);
    }

    public void postSendMessage(String userId,  String liveId,String userIcon,String userName,int userMark,int permission, int leibie,String tyonghuid,String tyonghunicheng,String chatContent,String tyonghutouxiang,String relation ){
        model.sendMessage(userId,liveId,userIcon,userName,userMark,permission,leibie,tyonghuid,tyonghunicheng,chatContent,tyonghutouxiang,relation);
    }
    @Override
    public void onChatSuccess(List<ChatPBean.DataBean.ChatBean> chat) {
        view.onChatSuccess(chat);
    }

    @Override
    public void onSendSuccess() {
        view.onSendSuccess();
    }

    @Override
    public void onChatFailed(String msg) {
        view.onChatFailed(msg);
    }

    public void saveCheck(String msgId) {

        model.saveCheck(msgId,view.getUserId());
    }

    public void deleteMessage(ChatPBean.DataBean.ChatBean chatBean) {
        model.deleteMessage(chatBean);
    }
}
