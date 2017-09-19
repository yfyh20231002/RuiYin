package com.unitesoft.huanrong.listener;

/**
 * Created by Mr.zhang on 2017/8/8.
 */

public interface OnLiveRoomChatTalkListener {
    void onClickTalkTo(String msgId,String userName,String userId,String userIcon,String content,int action); // 对他说
}
