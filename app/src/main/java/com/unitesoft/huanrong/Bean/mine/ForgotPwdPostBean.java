package com.unitesoft.huanrong.Bean.mine;

/**
 * Created by Mr.zhang on 2017/8/11.
 */

public class ForgotPwdPostBean {
    private String yonghushoujihao;
    private String newpwd;

    public ForgotPwdPostBean(String yonghushoujihao, String newpwd) {
        this.yonghushoujihao = yonghushoujihao;
        this.newpwd = newpwd;
    }

    public String getYonghushoujihao() {
        return yonghushoujihao;
    }

    public void setYonghushoujihao(String yonghushoujihao) {
        this.yonghushoujihao = yonghushoujihao;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }
}
