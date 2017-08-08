package com.example.shichang393.ruiyin.listener;

import com.example.shichang393.ruiyin.Bean.LiveBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public interface OnLiveListener {
    void success(List<LiveBean.DataBean.LiveRoomsBaseInfoBean> list);
    void failed(String message);
}
