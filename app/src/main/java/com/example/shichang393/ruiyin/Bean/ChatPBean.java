package com.example.shichang393.ruiyin.Bean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/31.
 */

public class ChatPBean {
    /**
     * syscode : 0
     * msg :
     * data : {"businesscode":300,"chat":[{"messageid":"BF35F5E8B3F449DA81F0485DECB1D5B0","delflg":"0","shuohuarenfrom":null,"shuohuarentogroup":null,"shuohuarentoperson":null,"xiaoxileibie":0,"shuohuashijian":1501470844000,"yishenhe":1,"yishanchu":0,"yihuifu":null,"yizhiding":0,"contenttype":null,"shuohuaneirong":"恩","type":null,"action":null,"fasongpingtai":null,"tozhiboshiid":"FE7C1D82811A4E7DB4BA84D77E141F0A","fyonghuid":"E22EA1ABDE274E0B875C65168568CE91","fyonghunicheng":"薛艳红","fzhanghaoleixing":8,"fyonghutouxiang":"templates/wdcs/image/icon/0051.jpg","fyonghubiaozhu":0,"tyonghuid":null,"tyonghunicheng":null,"tyonghutouxiang":null}]}
     */

    private int syscode;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * businesscode : 300
         * chat : [{"messageid":"BF35F5E8B3F449DA81F0485DECB1D5B0","delflg":"0","shuohuarenfrom":null,"shuohuarentogroup":null,"shuohuarentoperson":null,"xiaoxileibie":0,"shuohuashijian":1501470844000,"yishenhe":1,"yishanchu":0,"yihuifu":null,"yizhiding":0,"contenttype":null,"shuohuaneirong":"恩","type":null,"action":null,"fasongpingtai":null,"tozhiboshiid":"FE7C1D82811A4E7DB4BA84D77E141F0A","fyonghuid":"E22EA1ABDE274E0B875C65168568CE91","fyonghunicheng":"薛艳红","fzhanghaoleixing":8,"fyonghutouxiang":"templates/wdcs/image/icon/0051.jpg","fyonghubiaozhu":0,"tyonghuid":null,"tyonghunicheng":null,"tyonghutouxiang":null}]
         */

        private int businesscode;
        private List<ChatBean> chat;

        public int getBusinesscode() {
            return businesscode;
        }

        public void setBusinesscode(int businesscode) {
            this.businesscode = businesscode;
        }

        public List<ChatBean> getChat() {
            return chat;
        }

        public void setChat(List<ChatBean> chat) {
            this.chat = chat;
        }

        public static class ChatBean {
            /**
             * messageid : BF35F5E8B3F449DA81F0485DECB1D5B0
             * delflg : 0
             * shuohuarenfrom : null
             * shuohuarentogroup : null
             * shuohuarentoperson : null
             * xiaoxileibie : 0
             * shuohuashijian : 1501470844000
             * yishenhe : 1
             * yishanchu : 0
             * yihuifu : null
             * yizhiding : 0
             * contenttype : null
             * shuohuaneirong : 恩
             * type : null
             * action : null
             * fasongpingtai : null
             * tozhiboshiid : FE7C1D82811A4E7DB4BA84D77E141F0A
             * fyonghuid : E22EA1ABDE274E0B875C65168568CE91
             * fyonghunicheng : 薛艳红
             * fzhanghaoleixing : 8
             * fyonghutouxiang : templates/wdcs/image/icon/0051.jpg
             * fyonghubiaozhu : 0
             * tyonghuid : null
             * tyonghunicheng : null
             * tyonghutouxiang : null
             * relation:
             */

            private String type;
            private String fyonghuid;
            private String messageid;
            private String tozhiboshiid;
            private String fyonghutouxiang;
            private String fyonghunicheng;
            private int fyonghubiaozhu;
            private int fzhanghaoleixing;
            private int xiaoxileibie;
            private String tyonghuid;
            private String tyonghunicheng;
            private String shuohuaneirong;
            private int yishenhe;
            private String fasongpingtai;



            private  String relation;
            private String tyonghutouxiang;
            private String delflg;
            private Object shuohuarenfrom;
            private Object shuohuarentogroup;
            private Object shuohuarentoperson;
            private long shuohuashijian;
            private int yishanchu;
            private Object yihuifu;
            private int yizhiding;
            private Object contenttype;
            private Object action;

            public ChatBean(String type, String fyonghuid, String messageid, String tozhiboshiid, String fyonghutouxiang, String fyonghunicheng, int fyonghubiaozhu, int fzhanghaoleixing, int xiaoxileibie, String tyonghuid, String tyonghunicheng, String shuohuaneirong, int yishenhe, String fasongpingtai) {
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
            }

            public String getRelation() {
                return relation;
            }

            public void setRelation(String relation) {
                this.relation = relation;
            }

            public String getMessageid() {
                return messageid;
            }

            public void setMessageid(String messageid) {
                this.messageid = messageid;
            }

            public String getDelflg() {
                return delflg;
            }

            public void setDelflg(String delflg) {
                this.delflg = delflg;
            }

            public Object getShuohuarenfrom() {
                return shuohuarenfrom;
            }

            public void setShuohuarenfrom(Object shuohuarenfrom) {
                this.shuohuarenfrom = shuohuarenfrom;
            }

            public Object getShuohuarentogroup() {
                return shuohuarentogroup;
            }

            public void setShuohuarentogroup(Object shuohuarentogroup) {
                this.shuohuarentogroup = shuohuarentogroup;
            }

            public Object getShuohuarentoperson() {
                return shuohuarentoperson;
            }

            public void setShuohuarentoperson(Object shuohuarentoperson) {
                this.shuohuarentoperson = shuohuarentoperson;
            }

            public int getXiaoxileibie() {
                return xiaoxileibie;
            }

            public void setXiaoxileibie(int xiaoxileibie) {
                this.xiaoxileibie = xiaoxileibie;
            }

            public long getShuohuashijian() {
                return shuohuashijian;
            }

            public void setShuohuashijian(long shuohuashijian) {
                this.shuohuashijian = shuohuashijian;
            }

            public int getYishenhe() {
                return yishenhe;
            }

            public void setYishenhe(int yishenhe) {
                this.yishenhe = yishenhe;
            }

            public int getYishanchu() {
                return yishanchu;
            }

            public void setYishanchu(int yishanchu) {
                this.yishanchu = yishanchu;
            }

            public Object getYihuifu() {
                return yihuifu;
            }

            public void setYihuifu(Object yihuifu) {
                this.yihuifu = yihuifu;
            }

            public int getYizhiding() {
                return yizhiding;
            }

            public void setYizhiding(int yizhiding) {
                this.yizhiding = yizhiding;
            }

            public Object getContenttype() {
                return contenttype;
            }

            public void setContenttype(Object contenttype) {
                this.contenttype = contenttype;
            }

            public String getShuohuaneirong() {
                return shuohuaneirong;
            }

            public void setShuohuaneirong(String shuohuaneirong) {
                this.shuohuaneirong = shuohuaneirong;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getAction() {
                return action;
            }

            public void setAction(Object action) {
                this.action = action;
            }

            public String getFasongpingtai() {
                return fasongpingtai;
            }

            public void setFasongpingtai(String fasongpingtai) {
                this.fasongpingtai = fasongpingtai;
            }

            public String getTozhiboshiid() {
                return tozhiboshiid;
            }

            public void setTozhiboshiid(String tozhiboshiid) {
                this.tozhiboshiid = tozhiboshiid;
            }

            public String getFyonghuid() {
                return fyonghuid;
            }

            public void setFyonghuid(String fyonghuid) {
                this.fyonghuid = fyonghuid;
            }

            public String getFyonghunicheng() {
                return fyonghunicheng;
            }

            public void setFyonghunicheng(String fyonghunicheng) {
                this.fyonghunicheng = fyonghunicheng;
            }

            public int getFzhanghaoleixing() {
                return fzhanghaoleixing;
            }

            public void setFzhanghaoleixing(int fzhanghaoleixing) {
                this.fzhanghaoleixing = fzhanghaoleixing;
            }

            public String getFyonghutouxiang() {
                return fyonghutouxiang;
            }

            public void setFyonghutouxiang(String fyonghutouxiang) {
                this.fyonghutouxiang = fyonghutouxiang;
            }

            public int getFyonghubiaozhu() {
                return fyonghubiaozhu;
            }

            public void setFyonghubiaozhu(int fyonghubiaozhu) {
                this.fyonghubiaozhu = fyonghubiaozhu;
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

            public String getTyonghutouxiang() {
                return tyonghutouxiang;
            }

            public void setTyonghutouxiang(String tyonghutouxiang) {
                this.tyonghutouxiang = tyonghutouxiang;
            }
        }
    }
}
