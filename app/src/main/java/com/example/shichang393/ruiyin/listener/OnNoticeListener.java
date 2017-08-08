package com.example.shichang393.ruiyin.listener;

import com.example.shichang393.ruiyin.Bean.NoticeBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/26.
 */

public interface OnNoticeListener {
    void success(List<NoticeBean> list);
    void failed(String errormessage);
}
