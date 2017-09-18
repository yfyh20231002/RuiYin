package com.tianjin.huanrong.listener;


import com.tianjin.huanrong.Bean.FlashBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/23.
 */

public interface OnFlashListener  {
    void success(List<FlashBean.DataBean.DangtianshujuBean> list);
    void failed(String errormessage);
}
