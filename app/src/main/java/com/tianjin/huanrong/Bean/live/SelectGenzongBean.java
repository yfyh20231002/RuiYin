package com.tianjin.huanrong.Bean.live;

/**
 * Created by Mr.zhang on 2017/8/17.
 */

public class SelectGenzongBean {


    /**
     * tradingid : 01ED927F84664B7491E64CF6ACC32290
     * delflg : 0
     * fenxishixingming : DAE20C728DF145FA9B331922929823EE
     * variety : 咖啡
     * jiancangfangxiang : 做多
     * openprice : 1
     * stopprice : 3
     * surplusprice :
     * closeprice : 2
     * createdate : 1502949152000
     * updatedate : null
     * ifzhudan : 1
     * jianyileixing : 0
     * fenxishitouxiang : /upload/kehuziliaoguanli/201601/1452567336927.png
     */

    private String tradingid;
    private String delflg;
    private String fenxishixingming;
    private String variety;
    private String jiancangfangxiang;
    private String openprice;
    private String stopprice;
    private String surplusprice;
    private String closeprice;
    private long createdate;
    private Object updatedate;
    private int ifzhudan;
    private int jianyileixing;
    private String fenxishitouxiang;

    public SelectGenzongBean(String variety, String jiancangfangxiang, String openprice, String closeprice,String stopprice, int ifzhudan) {
        this.variety = variety;
        this.jiancangfangxiang = jiancangfangxiang;
        this.openprice = openprice;
        this.closeprice = closeprice;
        this.ifzhudan = ifzhudan;
        this.stopprice=stopprice;
    }

    public String getTradingid() {
        return tradingid;
    }

    public void setTradingid(String tradingid) {
        this.tradingid = tradingid;
    }

    public String getDelflg() {
        return delflg;
    }

    public void setDelflg(String delflg) {
        this.delflg = delflg;
    }

    public String getFenxishixingming() {
        return fenxishixingming;
    }

    public void setFenxishixingming(String fenxishixingming) {
        this.fenxishixingming = fenxishixingming;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getJiancangfangxiang() {
        return jiancangfangxiang;
    }

    public void setJiancangfangxiang(String jiancangfangxiang) {
        this.jiancangfangxiang = jiancangfangxiang;
    }

    public String getOpenprice() {
        return openprice;
    }

    public void setOpenprice(String openprice) {
        this.openprice = openprice;
    }

    public String getStopprice() {
        return stopprice;
    }

    public void setStopprice(String stopprice) {
        this.stopprice = stopprice;
    }

    public String getSurplusprice() {
        return surplusprice;
    }

    public void setSurplusprice(String surplusprice) {
        this.surplusprice = surplusprice;
    }

    public String getCloseprice() {
        return closeprice;
    }

    public void setCloseprice(String closeprice) {
        this.closeprice = closeprice;
    }

    public long getCreatedate() {
        return createdate;
    }

    public void setCreatedate(long createdate) {
        this.createdate = createdate;
    }

    public Object getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Object updatedate) {
        this.updatedate = updatedate;
    }

    public int getIfzhudan() {
        return ifzhudan;
    }

    public void setIfzhudan(int ifzhudan) {
        this.ifzhudan = ifzhudan;
    }

    public int getJianyileixing() {
        return jianyileixing;
    }

    public void setJianyileixing(int jianyileixing) {
        this.jianyileixing = jianyileixing;
    }

    public String getFenxishitouxiang() {
        return fenxishitouxiang;
    }

    public void setFenxishitouxiang(String fenxishitouxiang) {
        this.fenxishitouxiang = fenxishitouxiang;
    }
}
