package com.unitesoft.huanrong.Bean.mine;

/**
 * Created by Mr.zhang on 2017/8/11.
 */

public class UseRegisterPostBean {
    private String yonghushoujihao;
    private String zhucepingtai;

    public UseRegisterPostBean(String yonghushoujihao, String zhucepingtai) {
        this.yonghushoujihao = yonghushoujihao;
        this.zhucepingtai = zhucepingtai;
    }

    public String getYonghushoujihao() {
        return yonghushoujihao;
    }

    public void setYonghushoujihao(String yonghushoujihao) {
        this.yonghushoujihao = yonghushoujihao;
    }

    public String getZhucepingtai() {
        return zhucepingtai;
    }

    public void setZhucepingtai(String zhucepingtai) {
        this.zhucepingtai = zhucepingtai;
    }
}
