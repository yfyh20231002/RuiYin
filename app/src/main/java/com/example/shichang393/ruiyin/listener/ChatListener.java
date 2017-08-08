package com.example.shichang393.ruiyin.listener;

/**
 * 作者：包长青
 * 时间：2016/4/28 15:48
 * 概要：to do what
 * 版本：1.0
 */

public interface ChatListener {

    void open();

    void receive(String msg);

    void error(String remind);

}
