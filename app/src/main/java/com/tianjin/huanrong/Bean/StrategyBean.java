package com.tianjin.huanrong.Bean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/18.
 */

public class StrategyBean {
    /**
     * syscode : 0
     * msg :
     * data : {"businesscode":400,"PROPOSAL":[{"messageid":"26CB7F4ADA43491194D14A43CC6532DA","delflg":"0","shuohuarenfrom":"9597B2B3082C491AB5350F729C6195E2","shuohuarentogroup":"FE7C1D82811A4E7DB4BA84D77E141F0A","shuohuarentoperson":"","xiaoxileibie":2,"shuohuashijian":1500339261000,"yishenhe":1,"yishanchu":0,"yihuifu":null,"yizhiding":0,"contenttype":null,"shuohuaneirong":"柏油操作建议：可于2310--2320价格附近做多，盈利目标点为2380--2390,建议将止损价格设置为2275。本操作建议为分析师个人意见，仅供参考。","type":null,"action":null,"fasongpingtai":null,"tozhiboshiid":null,"fyonghuid":null,"fyonghunicheng":"戚鹏","fzhanghaoleixing":null,"fyonghutouxiang":"/upload/kehuziliaoguanli/201601/1452567404335.png","fyonghubiaozhu":null,"tyonghuid":null,"tyonghunicheng":null,"tyonghutouxiang":null},{"messageid":"347FC173B06D4A6CA1BE548E03D4F3EF","delflg":"0","shuohuarenfrom":"9597B2B3082C491AB5350F729C6195E2","shuohuarentogroup":"FE7C1D82811A4E7DB4BA84D77E141F0A","shuohuarentoperson":"","xiaoxileibie":2,"shuohuashijian":1500339631000,"yishenhe":1,"yishanchu":0,"yihuifu":null,"yizhiding":0,"contenttype":null,"shuohuaneirong":"【一带一路】白银操作建议：可于3525--3535价格附近做空，盈利目标点为3470--3480,建议将止损价格设置为3565。本操作建议为分析师个人意见，仅供参考。","type":null,"action":null,"fasongpingtai":null,"tozhiboshiid":null,"fyonghuid":null,"fyonghunicheng":"戚鹏","fzhanghaoleixing":null,"fyonghutouxiang":"/upload/kehuziliaoguanli/201601/1452567404335.png","fyonghubiaozhu":null,"tyonghuid":null,"tyonghunicheng":null,"tyonghutouxiang":null},{"messageid":"D9A5504A18154B798BA5C144CBC6825F","delflg":"0","shuohuarenfrom":"DAE20C728DF145FA9B331922929823EE","shuohuarentogroup":"FE7C1D82811A4E7DB4BA84D77E141F0A","shuohuarentoperson":"","xiaoxileibie":2,"shuohuashijian":1500339984000,"yishenhe":1,"yishanchu":0,"yihuifu":null,"yizhiding":0,"contenttype":null,"shuohuaneirong":"【一带一路】白银操作建议：可于3485-3475价格附近做多，盈利目标点为3525-3545,建议将止损价格设置为3445。本操作建议为分析师个人意见，仅供参考。","type":null,"action":null,"fasongpingtai":null,"tozhiboshiid":null,"fyonghuid":null,"fyonghunicheng":"李亚蓓","fzhanghaoleixing":null,"fyonghutouxiang":"/upload/kehuziliaoguanli/201601/1452567336927.png","fyonghubiaozhu":null,"tyonghuid":null,"tyonghunicheng":null,"tyonghutouxiang":null},{"messageid":"32FF9C9FC31C4A9A8EB42974892FFEDE","delflg":"0","shuohuarenfrom":"DAE20C728DF145FA9B331922929823EE","shuohuarentogroup":"FE7C1D82811A4E7DB4BA84D77E141F0A","shuohuarentoperson":"","xiaoxileibie":2,"shuohuashijian":1500340022000,"yishenhe":1,"yishanchu":0,"yihuifu":null,"yizhiding":0,"contenttype":null,"shuohuaneirong":"柏油操作建议：可于2355-2365价格附近做空，盈利目标点为2310-2300,建议将止损价格设置为2385。本操作建议为分析师个人意见，仅供参考。","type":null,"action":null,"fasongpingtai":null,"tozhiboshiid":null,"fyonghuid":null,"fyonghunicheng":"李亚蓓","fzhanghaoleixing":null,"fyonghutouxiang":"/upload/kehuziliaoguanli/201601/1452567336927.png","fyonghubiaozhu":null,"tyonghuid":null,"tyonghunicheng":null,"tyonghutouxiang":null}]}
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
         * businesscode : 400
         * PROPOSAL : [{"messageid":"26CB7F4ADA43491194D14A43CC6532DA","delflg":"0","shuohuarenfrom":"9597B2B3082C491AB5350F729C6195E2","shuohuarentogroup":"FE7C1D82811A4E7DB4BA84D77E141F0A","shuohuarentoperson":"","xiaoxileibie":2,"shuohuashijian":1500339261000,"yishenhe":1,"yishanchu":0,"yihuifu":null,"yizhiding":0,"contenttype":null,"shuohuaneirong":"柏油操作建议：可于2310--2320价格附近做多，盈利目标点为2380--2390,建议将止损价格设置为2275。本操作建议为分析师个人意见，仅供参考。","type":null,"action":null,"fasongpingtai":null,"tozhiboshiid":null,"fyonghuid":null,"fyonghunicheng":"戚鹏","fzhanghaoleixing":null,"fyonghutouxiang":"/upload/kehuziliaoguanli/201601/1452567404335.png","fyonghubiaozhu":null,"tyonghuid":null,"tyonghunicheng":null,"tyonghutouxiang":null},{"messageid":"347FC173B06D4A6CA1BE548E03D4F3EF","delflg":"0","shuohuarenfrom":"9597B2B3082C491AB5350F729C6195E2","shuohuarentogroup":"FE7C1D82811A4E7DB4BA84D77E141F0A","shuohuarentoperson":"","xiaoxileibie":2,"shuohuashijian":1500339631000,"yishenhe":1,"yishanchu":0,"yihuifu":null,"yizhiding":0,"contenttype":null,"shuohuaneirong":"【一带一路】白银操作建议：可于3525--3535价格附近做空，盈利目标点为3470--3480,建议将止损价格设置为3565。本操作建议为分析师个人意见，仅供参考。","type":null,"action":null,"fasongpingtai":null,"tozhiboshiid":null,"fyonghuid":null,"fyonghunicheng":"戚鹏","fzhanghaoleixing":null,"fyonghutouxiang":"/upload/kehuziliaoguanli/201601/1452567404335.png","fyonghubiaozhu":null,"tyonghuid":null,"tyonghunicheng":null,"tyonghutouxiang":null},{"messageid":"D9A5504A18154B798BA5C144CBC6825F","delflg":"0","shuohuarenfrom":"DAE20C728DF145FA9B331922929823EE","shuohuarentogroup":"FE7C1D82811A4E7DB4BA84D77E141F0A","shuohuarentoperson":"","xiaoxileibie":2,"shuohuashijian":1500339984000,"yishenhe":1,"yishanchu":0,"yihuifu":null,"yizhiding":0,"contenttype":null,"shuohuaneirong":"【一带一路】白银操作建议：可于3485-3475价格附近做多，盈利目标点为3525-3545,建议将止损价格设置为3445。本操作建议为分析师个人意见，仅供参考。","type":null,"action":null,"fasongpingtai":null,"tozhiboshiid":null,"fyonghuid":null,"fyonghunicheng":"李亚蓓","fzhanghaoleixing":null,"fyonghutouxiang":"/upload/kehuziliaoguanli/201601/1452567336927.png","fyonghubiaozhu":null,"tyonghuid":null,"tyonghunicheng":null,"tyonghutouxiang":null},{"messageid":"32FF9C9FC31C4A9A8EB42974892FFEDE","delflg":"0","shuohuarenfrom":"DAE20C728DF145FA9B331922929823EE","shuohuarentogroup":"FE7C1D82811A4E7DB4BA84D77E141F0A","shuohuarentoperson":"","xiaoxileibie":2,"shuohuashijian":1500340022000,"yishenhe":1,"yishanchu":0,"yihuifu":null,"yizhiding":0,"contenttype":null,"shuohuaneirong":"柏油操作建议：可于2355-2365价格附近做空，盈利目标点为2310-2300,建议将止损价格设置为2385。本操作建议为分析师个人意见，仅供参考。","type":null,"action":null,"fasongpingtai":null,"tozhiboshiid":null,"fyonghuid":null,"fyonghunicheng":"李亚蓓","fzhanghaoleixing":null,"fyonghutouxiang":"/upload/kehuziliaoguanli/201601/1452567336927.png","fyonghubiaozhu":null,"tyonghuid":null,"tyonghunicheng":null,"tyonghutouxiang":null}]
         */

        private int businesscode;
        private List<PROPOSALBean> PROPOSAL;

        public int getBusinesscode() {
            return businesscode;
        }

        public void setBusinesscode(int businesscode) {
            this.businesscode = businesscode;
        }

        public List<PROPOSALBean> getPROPOSAL() {
            return PROPOSAL;
        }

        public void setPROPOSAL(List<PROPOSALBean> PROPOSAL) {
            this.PROPOSAL = PROPOSAL;
        }

        public static class PROPOSALBean {
            /**
             * messageid : 26CB7F4ADA43491194D14A43CC6532DA
             * delflg : 0
             * shuohuarenfrom : 9597B2B3082C491AB5350F729C6195E2
             * shuohuarentogroup : FE7C1D82811A4E7DB4BA84D77E141F0A
             * shuohuarentoperson :
             * xiaoxileibie : 2
             * shuohuashijian : 1500339261000
             * yishenhe : 1
             * yishanchu : 0
             * yihuifu : null
             * yizhiding : 0
             * contenttype : null
             * shuohuaneirong : 柏油操作建议：可于2310--2320价格附近做多，盈利目标点为2380--2390,建议将止损价格设置为2275。本操作建议为分析师个人意见，仅供参考。
             * type : null
             * action : null
             * fasongpingtai : null
             * tozhiboshiid : null
             * fyonghuid : null
             * fyonghunicheng : 戚鹏
             * fzhanghaoleixing : null
             * fyonghutouxiang : /upload/kehuziliaoguanli/201601/1452567404335.png
             * fyonghubiaozhu : null
             * tyonghuid : null
             * tyonghunicheng : null
             * tyonghutouxiang : null
             */

            private String messageid;
            private String delflg;
            private String shuohuarenfrom;
            private String shuohuarentogroup;
            private String shuohuarentoperson;
            private int xiaoxileibie;
            private long shuohuashijian;
            private int yishenhe;
            private int yishanchu;
            private Object yihuifu;
            private int yizhiding;
            private Object contenttype;
            private String shuohuaneirong;
            private Object type;
            private Object action;
            private Object fasongpingtai;
            private Object tozhiboshiid;
            private Object fyonghuid;
            private String fyonghunicheng;
            private Object fzhanghaoleixing;
            private String fyonghutouxiang;
            private Object fyonghubiaozhu;
            private Object tyonghuid;
            private Object tyonghunicheng;
            private Object tyonghutouxiang;

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

            public String getShuohuarenfrom() {
                return shuohuarenfrom;
            }

            public void setShuohuarenfrom(String shuohuarenfrom) {
                this.shuohuarenfrom = shuohuarenfrom;
            }

            public String getShuohuarentogroup() {
                return shuohuarentogroup;
            }

            public void setShuohuarentogroup(String shuohuarentogroup) {
                this.shuohuarentogroup = shuohuarentogroup;
            }

            public String getShuohuarentoperson() {
                return shuohuarentoperson;
            }

            public void setShuohuarentoperson(String shuohuarentoperson) {
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

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public Object getAction() {
                return action;
            }

            public void setAction(Object action) {
                this.action = action;
            }

            public Object getFasongpingtai() {
                return fasongpingtai;
            }

            public void setFasongpingtai(Object fasongpingtai) {
                this.fasongpingtai = fasongpingtai;
            }

            public Object getTozhiboshiid() {
                return tozhiboshiid;
            }

            public void setTozhiboshiid(Object tozhiboshiid) {
                this.tozhiboshiid = tozhiboshiid;
            }

            public Object getFyonghuid() {
                return fyonghuid;
            }

            public void setFyonghuid(Object fyonghuid) {
                this.fyonghuid = fyonghuid;
            }

            public String getFyonghunicheng() {
                return fyonghunicheng;
            }

            public void setFyonghunicheng(String fyonghunicheng) {
                this.fyonghunicheng = fyonghunicheng;
            }

            public Object getFzhanghaoleixing() {
                return fzhanghaoleixing;
            }

            public void setFzhanghaoleixing(Object fzhanghaoleixing) {
                this.fzhanghaoleixing = fzhanghaoleixing;
            }

            public String getFyonghutouxiang() {
                return fyonghutouxiang;
            }

            public void setFyonghutouxiang(String fyonghutouxiang) {
                this.fyonghutouxiang = fyonghutouxiang;
            }

            public Object getFyonghubiaozhu() {
                return fyonghubiaozhu;
            }

            public void setFyonghubiaozhu(Object fyonghubiaozhu) {
                this.fyonghubiaozhu = fyonghubiaozhu;
            }

            public Object getTyonghuid() {
                return tyonghuid;
            }

            public void setTyonghuid(Object tyonghuid) {
                this.tyonghuid = tyonghuid;
            }

            public Object getTyonghunicheng() {
                return tyonghunicheng;
            }

            public void setTyonghunicheng(Object tyonghunicheng) {
                this.tyonghunicheng = tyonghunicheng;
            }

            public Object getTyonghutouxiang() {
                return tyonghutouxiang;
            }

            public void setTyonghutouxiang(Object tyonghutouxiang) {
                this.tyonghutouxiang = tyonghutouxiang;
            }
        }
    }
}
