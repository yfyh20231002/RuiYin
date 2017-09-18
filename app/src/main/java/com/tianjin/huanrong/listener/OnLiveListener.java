package com.tianjin.huanrong.listener;


import com.tianjin.huanrong.Bean.LiveBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public interface OnLiveListener {
    void success(List<LiveBean.DataBean.LiveRoomsBaseInfoBean> list);
    void failed(String message);
}
