package com.example.shichang393.ruiyin.view;

import com.example.shichang393.ruiyin.Bean.ChatPBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/8/1.
 */

public interface InteractionView {
    String getUserId();
    void onChatSuccess(List<ChatPBean.DataBean.ChatBean> chat);
    void onSendSuccess();
    void onChatFailed(String msg);
}
