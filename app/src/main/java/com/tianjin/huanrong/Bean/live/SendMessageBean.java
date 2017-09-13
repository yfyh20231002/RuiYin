package com.tianjin.huanrong.Bean.live;

/**
 * Created by Mr.zhang on 2017/8/17.
 */

public class SendMessageBean {

    /**
     * syscode : 0
     * msg : {"fasongpingtai":"1","fyonghubiaozhu":0,"fyonghuid":"DAE20C728DF145FA9B331922929823EE","fyonghunicheng":"李亚蓓","fyonghutouxiang":"/upload/kehuziliaoguanli/201601/1452567336927.png","fzhanghaoleixing":1,"messageid":"D3532AFA997F49B391D8496CE0B6EBDC","relation":"","shuohuaneirong":"咖啡操作建议：可于2价格附近做多，盈利目标点为3，建议将止损价格设置为4。本操作建议为分析师个人意见，仅供参考。","shuohuarenfrom":"DAE20C728DF145FA9B331922929823EE","shuohuarentogroup":"FE7C1D82811A4E7DB4BA84D77E141F0A","shuohuarentoperson":"","shuohuashijian":1502940000281,"tozhiboshiid":"FE7C1D82811A4E7DB4BA84D77E141F0A","tyonghuid":"","tyonghunicheng":"","tyonghutouxiang":"","type":"message","xiaoxileibie":2,"yishenhe":0}
     */

    private int syscode;
    private String msg;

    public int getSyscode() {
        return syscode;
    }

    public void setSyscode(int syscode) {
        this.syscode = syscode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
