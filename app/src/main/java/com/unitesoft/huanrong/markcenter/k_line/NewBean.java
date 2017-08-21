package com.unitesoft.huanrong.markcenter.k_line;

/**
 * Created by shichang387 on 2017-8-3.
 */

public class NewBean {
    private float o;
    private float h;

    public NewBean(float o, float h, float l, float c, String t) {
        this.o = o;
        this.h = h;
        this.l = l;
        this.c = c;
        this.t = t;
    }

    public float getO() {
        return o;
    }

    public void setO(float o) {
        this.o = o;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getL() {
        return l;
    }

    public void setL(float l) {
        this.l = l;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    private float l;
    private float c;
    private String t;
}
