package com.unitesoft.huanrong.Bean;


public class SendMessagePostBean {
    private String type;
    private String fyonghuid;
    private String messageid;
    private String tozhiboshiid;
    private String fyonghutouxiang;
    private String fyonghunicheng;
    private  int fyonghubiaozhu;
    private int fzhanghaoleixing;
    private int xiaoxileibie;
    private String tyonghuid;
    private String tyonghunicheng;
    private String shuohuaneirong;
    private int yishenhe;
    private String fasongpingtai;
    private String relation;
    private String tyonghutouxiang;

    public SendMessagePostBean(String type, String fyonghuid, String messageid, String tozhiboshiid, String fyonghutouxiang, String fyonghunicheng, int fyonghubiaozhu, int fzhanghaoleixing, int xiaoxileibie, String tyonghuid, String tyonghunicheng, String shuohuaneirong, int yishenhe, String fasongpingtai, String tyonghutouxiang, String relation) {
        this.type = type;
        this.fyonghuid = fyonghuid;
        this.messageid = messageid;
        this.tozhiboshiid = tozhiboshiid;
        this.fyonghutouxiang = fyonghutouxiang;
        this.fyonghunicheng = fyonghunicheng;
        this.fyonghubiaozhu = fyonghubiaozhu;
        this.fzhanghaoleixing = fzhanghaoleixing;
        this.xiaoxileibie = xiaoxileibie;
        this.tyonghuid = tyonghuid;
        this.tyonghunicheng = tyonghunicheng;
        this.shuohuaneirong = shuohuaneirong;
        this.yishenhe = yishenhe;
        this.fasongpingtai = fasongpingtai;
        this.tyonghutouxiang=tyonghutouxiang;
        this.relation=relation;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
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

    public String getFyonghutouxiang() {
        return fyonghutouxiang;
    }

    public void setFyonghutouxiang(String fyonghutouxiang) {
        this.fyonghutouxiang = fyonghutouxiang;
    }

    public String getFyonghunicheng() {
        return fyonghunicheng;
    }

    public void setFyonghunicheng(String fyonghunicheng) {
        this.fyonghunicheng = fyonghunicheng;
    }

    public int getFyonghubiaozhu() {
        return fyonghubiaozhu;
    }

    public void setFyonghubiaozhu(int fyonghubiaozhu) {
        this.fyonghubiaozhu = fyonghubiaozhu;
    }

    public void setFzhanghaoleixing(int fzhanghaoleixing) {
        this.fzhanghaoleixing = fzhanghaoleixing;
    }


    public int getXiaoxileibie() {
        return xiaoxileibie;
    }

    public void setXiaoxileibie(int xiaoxileibie) {
        this.xiaoxileibie = xiaoxileibie;
    }

    public String getTyonghuid() {
        return tyonghuid;
    }

    public void setTyonghuid(String tyonghuid) {
        this.tyonghuid = tyonghuid;
    }

    public String getTyonghunicheng() {
        return tyonghunicheng;
    }

    public void setTyonghunicheng(String tyonghunicheng) {
        this.tyonghunicheng = tyonghunicheng;
    }

    public String getShuohuaneirong() {
        return shuohuaneirong;
    }

    public void setShuohuaneirong(String shuohuaneirong) {
        this.shuohuaneirong = shuohuaneirong;
    }

    public int getYishenhe() {
        return yishenhe;
    }

    public void setYishenhe(int yishenhe) {
        this.yishenhe = yishenhe;
    }

    public int getFzhanghaoleixing() {

        return fzhanghaoleixing;
    }

    public String getFasongpingtai() {
        return fasongpingtai;
    }

    public void setFasongpingtai(String fasongpingtai) {
        this.fasongpingtai = fasongpingtai;
    }
}
