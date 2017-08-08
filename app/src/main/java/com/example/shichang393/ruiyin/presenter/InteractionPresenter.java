package com.example.shichang393.ruiyin.presenter;

import com.example.shichang393.ruiyin.Bean.ChatPBean;
import com.example.shichang393.ruiyin.listener.OnInteractionListener;
import com.example.shichang393.ruiyin.model.InteractionModel;
import com.example.shichang393.ruiyin.view.InteractionView;

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

    public void postSendMessage(String userId,  String liveId,String userIcon,String userName,int userMark,int permission, int leibie,String chatContent){
        model.sendMessage(userId,liveId,userIcon,userName,userMark,permission,leibie,chatContent);
    }
    @Override
    public void onChatSuccess(List<ChatPBean.DataBean.ChatBean> chat) {
        view.onChatSuccess(chat);
    }

    @Override
    public void onChatFailed(String msg) {
        view.onChatFailed(msg);
    }
}
