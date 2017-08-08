package com.example.shichang393.ruiyin.view;

import com.example.shichang393.ruiyin.Bean.CurriculumBean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/21.
 */

public interface CurriculumView {
    void success(List<CurriculumBean.PaibanBean> list,List<CurriculumBean.YhtxBean> yhtx);
    void failed(String msg);
}
