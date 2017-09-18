package com.tianjin.huanrong.listener;

/**
 * Created by Mr.zhang on 2017/7/21.
 * 简介
 */

public interface OnSynopsisListener {
    void success(String imageurl);
    void failed(String msg);
}
