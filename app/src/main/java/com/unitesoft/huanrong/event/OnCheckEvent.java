package com.unitesoft.huanrong.event;

/**
 * 作者：包长青
 * 时间：2016/5/3 16:34
 * 概要：to do what
 * 版本：1.0
 */

public class OnCheckEvent {

    private int click;
    private int position;
    private String messageId;
    private String userName;
    private String userId;
    private String userIcon;
    private String chatContent;

    public OnCheckEvent(int click, int position, String messageId, String userName, String userId, String userIcon, String chatContent) {
        this.click = click;
        this.position = position;
        this.messageId = messageId;
        this.userName = userName;
        this.userId = userId;
        this.userIcon = userIcon;
        this.chatContent = chatContent;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public int getClick() {
        return click;
    }

    public int getPosition() {
        return position;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }
}
