package com.tianjin.huanrong.view;


import com.tianjin.huanrong.Bean.FlashBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/23.
 */

public interface FlashView {
    void success(List<FlashBean.DataBean.DangtianshujuBean> list);
    void failed(String errormessage);
}
