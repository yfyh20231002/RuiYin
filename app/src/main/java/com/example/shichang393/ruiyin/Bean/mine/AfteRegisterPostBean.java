package com.example.shichang393.ruiyin.Bean.mine;

/**
 * Created by Mr.zhang on 2017/8/11.
 */

public class AfteRegisterPostBean {
    private String yonghushoujihao;
    private String  yonghumima;
    private String zhucepingtai;

    public AfteRegisterPostBean(String yonghushoujihao, String yonghumima, String zhucepingtai) {
        this.yonghushoujihao = yonghushoujihao;
        this.yonghumima = yonghumima;
        this.zhucepingtai = zhucepingtai;
    }

    public String getYonghushoujihao() {
        return yonghushoujihao;
    }

    public void setYonghushoujihao(String yonghushoujihao) {
        this.yonghushoujihao = yonghushoujihao;
    }

    public String getYonghumima() {
        return yonghumima;
    }

    public void setYonghumima(String yonghumima) {
        this.yonghumima = yonghumima;
    }

    public String getZhucepingtai() {
        return zhucepingtai;
    }

    public void setZhucepingtai(String zhucepingtai) {
        this.zhucepingtai = zhucepingtai;
    }
}
