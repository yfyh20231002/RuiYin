package com.tianjin.huanrong.Bean.live;

/**
 * Created by Mr.zhang on 2017/8/17.
 */

public class InsertCaoZuoPostBean {
//        品种名称
    private String variety;
//    建仓价
    private String price;
//    建仓方向
    private String direction;
//    目标点位
    private String targetPoint;
//    止损点位
    private String stopPoint;
//    分析师ID
    private String fenxishiid;
//  是否爆单 是1，否0
    private String ifBaodan;
//    保存的messageid
    private String tradingid;
//   加仓价
    private String surplusprice;
//    分析师头像
    private String fyonghutouxiang;

    public InsertCaoZuoPostBean(String variety, String price, String direction, String targetPoint, String stopPoint, String fenxishiid, String ifBaodan, String tradingid, String surplusprice, String fyonghutouxiang) {
        this.variety = variety;
        this.price = price;
        this.direction = direction;
        this.targetPoint = targetPoint;
        this.stopPoint = stopPoint;
        this.fenxishiid = fenxishiid;
        this.ifBaodan = ifBaodan;
        this.tradingid = tradingid;
        this.surplusprice = surplusprice;
        this.fyonghutouxiang = fyonghutouxiang;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(String targetPoint) {
        this.targetPoint = targetPoint;
    }

    public String getStopPoint() {
        return stopPoint;
    }

    public void setStopPoint(String stopPoint) {
        this.stopPoint = stopPoint;
    }

    public String getFenxishiid() {
        return fenxishiid;
    }

    public void setFenxishiid(String fenxishiid) {
        this.fenxishiid = fenxishiid;
    }

    public String getIfBaodan() {
        return ifBaodan;
    }

    public void setIfBaodan(String ifBaodan) {
        this.ifBaodan = ifBaodan;
    }

    public String getTradingid() {
        return tradingid;
    }

    public void setTradingid(String tradingid) {
        this.tradingid = tradingid;
    }

    public String getSurplusprice() {
        return surplusprice;
    }

    public void setSurplusprice(String surplusprice) {
        this.surplusprice = surplusprice;
    }

    public String getFyonghutouxiang() {
        return fyonghutouxiang;
    }

    public void setFyonghutouxiang(String fyonghutouxiang) {
        this.fyonghutouxiang = fyonghutouxiang;
    }
}
