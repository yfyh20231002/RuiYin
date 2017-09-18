package com.tianjin.huanrong.listener;


import com.tianjin.huanrong.Bean.TokenBean;

/**
 * Created by Mr.zhang on 2017/7/6.
 */

public interface TokenListener {
    void success(TokenBean bean);
    void failde(String message);

}
