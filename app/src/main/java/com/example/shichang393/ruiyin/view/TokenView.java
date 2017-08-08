package com.example.shichang393.ruiyin.view;

import com.example.shichang393.ruiyin.Bean.TokenBean;

/**
 * Created by Mr.zhang on 2017/7/6.
 */

public interface TokenView {
    void success(TokenBean bean);
    void failde(String message);
}
