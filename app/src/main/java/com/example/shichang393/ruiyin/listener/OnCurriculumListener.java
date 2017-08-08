package com.example.shichang393.ruiyin.listener;

import com.example.shichang393.ruiyin.Bean.CurriculumBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/21.
 */

public interface OnCurriculumListener {
    void success(List<CurriculumBean.PaibanBean> list,List<CurriculumBean.YhtxBean> yhtx);
    void failed(String msg);
}
