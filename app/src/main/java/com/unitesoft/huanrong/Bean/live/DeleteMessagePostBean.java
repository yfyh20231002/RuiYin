package com.unitesoft.huanrong.Bean.live;

/**
 * Created by Mr.zhang on 2017/8/17.
 */

public class DeleteMessagePostBean {
    private String messageid;
    private String tozhiboshiid;
    private String yishanchu;
    private String type;
    private String fyonghuid;

    public DeleteMessagePostBean(String messageid, String tozhiboshiid, String yishanchu, String type, String fyonghuid) {
        this.messageid = messageid;
        this.tozhiboshiid = tozhiboshiid;
        this.yishanchu = yishanchu;
        this.type = type;
        this.fyonghuid = fyonghuid;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getTozhiboshiid() {
        return tozhiboshiid;
    }

    public void setTozhiboshiid(String tozhiboshiid) {
        this.tozhiboshiid = tozhiboshiid;
    }

    public String getYishanchu() {
        return yishanchu;
    }

    public void setYishanchu(String yishanchu) {
        this.yishanchu = yishanchu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFyonghuid() {
        return fyonghuid;
    }

    public void setFyonghuid(String fyonghuid) {
        this.fyonghuid = fyonghuid;
    }
}
