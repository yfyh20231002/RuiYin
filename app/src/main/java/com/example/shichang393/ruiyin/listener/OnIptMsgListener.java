package com.example.shichang393.ruiyin.listener;

import com.example.shichang393.ruiyin.Bean.IptMsgBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/27.
 */

public interface OnIptMsgListener {
    void success(List<IptMsgBean> list);
    void faild(String message);
}
