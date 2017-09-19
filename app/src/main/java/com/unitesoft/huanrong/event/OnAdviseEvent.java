package com.unitesoft.huanrong.event;

/**
 * Created by Mr.zhang on 2017/8/16.
 */

public class OnAdviseEvent {
    private int sendType;
    private String types;
    private String jianCang;
    private String round;
    private String jianCangUpdate;
    private String muBiao;
    private String zhiSun;
    private String baodan;

    public OnAdviseEvent(int sendType,String types, String jianCang, String round, String jianCangUpdate, String muBiao, String zhiSun,String baodan) {
        this.sendType=sendType;
        this.types = types;
        this.jianCang = jianCang;
        this.round = round;
        this.jianCangUpdate = jianCangUpdate;
        this.muBiao = muBiao;
        this.zhiSun = zhiSun;
        this.baodan=baodan;
    }

    public String getBaodan() {
        return baodan;
    }

    public void setBaodan(String baodan) {
        this.baodan = baodan;
    }

    public int getSendType() {
        return sendType;
    }

    public String getTypes() {
        return types;
    }

    public String getJianCang() {
        return jianCang;
    }

    public String getRound() {
        return round;
    }

    public String getJianCangUpdate() {
        return jianCangUpdate;
    }

    public String getMuBiao() {
        return muBiao;
    }

    public String getZhiSun() {
        return zhiSun;
    }
}
