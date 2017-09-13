package com.tianjin.huanrong.view;


import com.tianjin.huanrong.Bean.LiveBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public interface LiveView {
    void livesuccess(List<LiveBean.DataBean.LiveRoomsBaseInfoBean> list);
    void livefailed(String message);
}
