package com.example.shichang393.ruiyin.view;

import com.example.shichang393.ruiyin.Bean.NoticeBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/26.
 */

public interface NoticeView {
    void success(List<NoticeBean> list);
    void failed(String errormessage);
}
