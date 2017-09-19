package com.unitesoft.huanrong.listener;


import com.unitesoft.huanrong.Bean.IptMsgBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/27.
 */

public interface OnIptMsgListener {
    void success(List<IptMsgBean> list);
    void faild(String message);
}
