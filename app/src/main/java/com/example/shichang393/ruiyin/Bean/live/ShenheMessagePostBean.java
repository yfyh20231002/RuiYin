package com.example.shichang393.ruiyin.Bean.live;

/**
 * Created by Mr.zhang on 2017/8/17.
 */

public class ShenheMessagePostBean {
    private String messageid;
    private String fyonghuid;

    public ShenheMessagePostBean(String messageid, String fyonghuid) {
        this.messageid = messageid;
        this.fyonghuid = fyonghuid;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getFyonghuid() {
        return fyonghuid;
    }

    public void setFyonghuid(String fyonghuid) {
        this.fyonghuid = fyonghuid;
    }
}
