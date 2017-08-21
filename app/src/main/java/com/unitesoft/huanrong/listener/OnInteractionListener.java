package com.unitesoft.huanrong.listener;


import com.unitesoft.huanrong.Bean.ChatPBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/8/1.
 */

public interface OnInteractionListener {
    void onChatSuccess(List<ChatPBean.DataBean.ChatBean> chat);
    void onSendSuccess();
    void onChatFailed(String msg);
}
