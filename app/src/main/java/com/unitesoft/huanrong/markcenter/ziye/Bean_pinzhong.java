package com.unitesoft.huanrong.markcenter.ziye;

/**
 * Created by naive on 2017-7-6.
 */

public class Bean_pinzhong {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSj_code() {
        return sj_code;
    }

    public void setSj_code(String sj_code) {
        this.sj_code = sj_code;
    }

    private int id;
    private int sort;
    private String Fullname;
    private String code;
    private String sj_code;

    public String getShortname() {
        return Shortname;
    }

    public void setShortname(String shortname) {
        Shortname = shortname;
    }

    private String Shortname;
}
