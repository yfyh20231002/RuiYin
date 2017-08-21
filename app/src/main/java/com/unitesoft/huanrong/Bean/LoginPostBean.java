package com.unitesoft.huanrong.Bean;

/**
 * Created by Mr.zhang on 2017/8/10.
 */

public class LoginPostBean {
    private String zhanghao;
    private String mima;

    public LoginPostBean(String zhanghao, String mima) {
        this.zhanghao = zhanghao;
        this.mima = mima;
    }

    public String getZhanghao() {
        return zhanghao;
    }

    public void setZhanghao(String zhanghao) {
        this.zhanghao = zhanghao;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }
}
