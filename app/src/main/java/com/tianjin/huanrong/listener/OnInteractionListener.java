package com.tianjin.huanrong.listener;


import com.tianjin.huanrong.Bean.ChatPBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/8/1.
 */

public interface OnInteractionListener {
    void onChatSuccess(List<ChatPBean.DataBean.ChatBean> chat);
    void onSendSuccess();
    void tanchuang();
    void onChatFailed(String msg);
}
