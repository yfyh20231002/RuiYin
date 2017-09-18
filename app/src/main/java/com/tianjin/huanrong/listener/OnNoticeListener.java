package com.tianjin.huanrong.listener;


import com.tianjin.huanrong.Bean.NoticeBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/26.
 */

public interface OnNoticeListener {
    void success(List<NoticeBean> list);
    void failed(String errormessage);
}
