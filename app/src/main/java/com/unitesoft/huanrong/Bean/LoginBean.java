package com.unitesoft.huanrong.Bean;

/**
 * Created by Mr.zhang on 2017/8/10.
 */

public class LoginBean {

    /**
     * syscode : 0
     * msg :
     * data : {"businesscode":103,"users":{"yonghuid":"8AB54D148A4A4238B6E99792096791C8","delflg":"0","yonghushenfenzheng":"","yonghuxingming":"","yonghumima":"5B836A7F3D743FC97512F6FCA3736AB33D70E19810A802C366E2B331","yonghunicheng":"内测账号","yonghuyouxiang":"","yonghuxingbie":1,"yonghushengri":null,"shipanzhanghao":"","suoshuyewuquyu":null,"suoshuyewupinzhong":null,"suoshuyewujingli":"","baozhengjinyue":0,"rujinliang":20,"zhanghaoleixing":1,"yuangongsuoshubumen":null,"yonghuvip":null,"yonghujifen":0,"yonghushoujihao":"15200040846","shifoushijihuoyonghu":1,"yonghubiaozhu":0,"beikao":"","yonghutouxiang":"templates/wdcs/image/icon/0002.jpg","congyeshijian":0,"jiaoyimoshi":"","renzhengbianhao":"","jiaoyifengge":"","fensishuliang":0,"jinqizhanji":"","fenxishijieshaowangzhan":"","pingbifayanshijian":0,"pingbifayanliyou":null,"sirenzhiboshiid":"9D5D83C44D2E43969C060C2401B7C524","zhuceshijian":1472540678000,"fangkequanxian":1,"zhucepingtai":"0"}}
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
         * businesscode : 103
         * users : {"yonghuid":"8AB54D148A4A4238B6E99792096791C8","delflg":"0","yonghushenfenzheng":"","yonghuxingming":"","yonghumima":"5B836A7F3D743FC97512F6FCA3736AB33D70E19810A802C366E2B331","yonghunicheng":"内测账号","yonghuyouxiang":"","yonghuxingbie":1,"yonghushengri":null,"shipanzhanghao":"","suoshuyewuquyu":null,"suoshuyewupinzhong":null,"suoshuyewujingli":"","baozhengjinyue":0,"rujinliang":20,"zhanghaoleixing":1,"yuangongsuoshubumen":null,"yonghuvip":null,"yonghujifen":0,"yonghushoujihao":"15200040846","shifoushijihuoyonghu":1,"yonghubiaozhu":0,"beikao":"","yonghutouxiang":"templates/wdcs/image/icon/0002.jpg","congyeshijian":0,"jiaoyimoshi":"","renzhengbianhao":"","jiaoyifengge":"","fensishuliang":0,"jinqizhanji":"","fenxishijieshaowangzhan":"","pingbifayanshijian":0,"pingbifayanliyou":null,"sirenzhiboshiid":"9D5D83C44D2E43969C060C2401B7C524","zhuceshijian":1472540678000,"fangkequanxian":1,"zhucepingtai":"0"}
         */

        private int businesscode;
        private UsersBean users;

        public int getBusinesscode() {
            return businesscode;
        }

        public void setBusinesscode(int businesscode) {
            this.businesscode = businesscode;
        }

        public UsersBean getUsers() {
            return users;
        }

        public void setUsers(UsersBean users) {
            this.users = users;
        }

        public static class UsersBean {
            /**
             * yonghuid : 8AB54D148A4A4238B6E99792096791C8
             * delflg : 0
             * yonghushenfenzheng :
             * yonghuxingming :
             * yonghumima : 5B836A7F3D743FC97512F6FCA3736AB33D70E19810A802C366E2B331
             * yonghunicheng : 内测账号
             * yonghuyouxiang :
             * yonghuxingbie : 1
             * yonghushengri : null
             * shipanzhanghao :
             * suoshuyewuquyu : null
             * suoshuyewupinzhong : null
             * suoshuyewujingli :
             * baozhengjinyue : 0.0
             * rujinliang : 20.0
             * zhanghaoleixing : 1
             * yuangongsuoshubumen : null
             * yonghuvip : null
             * yonghujifen : 0
             * yonghushoujihao : 15200040846
             * shifoushijihuoyonghu : 1
             * yonghubiaozhu : 0
             * beikao :
             * yonghutouxiang : templates/wdcs/image/icon/0002.jpg
             * congyeshijian : 0
             * jiaoyimoshi :
             * renzhengbianhao :
             * jiaoyifengge :
             * fensishuliang : 0
             * jinqizhanji :
             * fenxishijieshaowangzhan :
             * pingbifayanshijian : 0
             * pingbifayanliyou : null
             * sirenzhiboshiid : 9D5D83C44D2E43969C060C2401B7C524
             * zhuceshijian : 1472540678000
             * fangkequanxian : 1
             * zhucepingtai : 0
             */

            private String yonghuid;
            private String delflg;
            private String yonghushenfenzheng;
            private String yonghuxingming;
            private String yonghumima;
            private String yonghunicheng;
            private String yonghuyouxiang;
            private int yonghuxingbie;
            private Object yonghushengri;
            private String shipanzhanghao;
            private Object suoshuyewuquyu;
            private Object suoshuyewupinzhong;
            private String suoshuyewujingli;
            private double baozhengjinyue;
            private double rujinliang;
            private int zhanghaoleixing;
            private Object yuangongsuoshubumen;
            private Object yonghuvip;
            private int yonghujifen;
            private String yonghushoujihao;
            private int shifoushijihuoyonghu;
            private int yonghubiaozhu;
            private String beikao;
            private String yonghutouxiang;
            private int congyeshijian;
            private String jiaoyimoshi;
            private String renzhengbianhao;
            private String jiaoyifengge;
            private int fensishuliang;
            private String jinqizhanji;
            private String fenxishijieshaowangzhan;
            private int pingbifayanshijian;
            private Object pingbifayanliyou;
            private String sirenzhiboshiid;
            private long zhuceshijian;
            private int fangkequanxian;
            private String zhucepingtai;

            public String getYonghuid() {
                return yonghuid;
            }

            public void setYonghuid(String yonghuid) {
                this.yonghuid = yonghuid;
            }

            public String getDelflg() {
                return delflg;
            }

            public void setDelflg(String delflg) {
                this.delflg = delflg;
            }

            public String getYonghushenfenzheng() {
                return yonghushenfenzheng;
            }

            public void setYonghushenfenzheng(String yonghushenfenzheng) {
                this.yonghushenfenzheng = yonghushenfenzheng;
            }

            public String getYonghuxingming() {
                return yonghuxingming;
            }

            public void setYonghuxingming(String yonghuxingming) {
                this.yonghuxingming = yonghuxingming;
            }

            public String getYonghumima() {
                return yonghumima;
            }

            public void setYonghumima(String yonghumima) {
                this.yonghumima = yonghumima;
            }

            public String getYonghunicheng() {
                return yonghunicheng;
            }

            public void setYonghunicheng(String yonghunicheng) {
                this.yonghunicheng = yonghunicheng;
            }

            public String getYonghuyouxiang() {
                return yonghuyouxiang;
            }

            public void setYonghuyouxiang(String yonghuyouxiang) {
                this.yonghuyouxiang = yonghuyouxiang;
            }

            public int getYonghuxingbie() {
                return yonghuxingbie;
            }

            public void setYonghuxingbie(int yonghuxingbie) {
                this.yonghuxingbie = yonghuxingbie;
            }

            public Object getYonghushengri() {
                return yonghushengri;
            }

            public void setYonghushengri(Object yonghushengri) {
                this.yonghushengri = yonghushengri;
            }

            public String getShipanzhanghao() {
                return shipanzhanghao;
            }

            public void setShipanzhanghao(String shipanzhanghao) {
                this.shipanzhanghao = shipanzhanghao;
            }

            public Object getSuoshuyewuquyu() {
                return suoshuyewuquyu;
            }

            public void setSuoshuyewuquyu(Object suoshuyewuquyu) {
                this.suoshuyewuquyu = suoshuyewuquyu;
            }

            public Object getSuoshuyewupinzhong() {
                return suoshuyewupinzhong;
            }

            public void setSuoshuyewupinzhong(Object suoshuyewupinzhong) {
                this.suoshuyewupinzhong = suoshuyewupinzhong;
            }

            public String getSuoshuyewujingli() {
                return suoshuyewujingli;
            }

            public void setSuoshuyewujingli(String suoshuyewujingli) {
                this.suoshuyewujingli = suoshuyewujingli;
            }

            public double getBaozhengjinyue() {
                return baozhengjinyue;
            }

            public void setBaozhengjinyue(double baozhengjinyue) {
                this.baozhengjinyue = baozhengjinyue;
            }

            public double getRujinliang() {
                return rujinliang;
            }

            public void setRujinliang(double rujinliang) {
                this.rujinliang = rujinliang;
            }

            public int getZhanghaoleixing() {
                return zhanghaoleixing;
            }

            public void setZhanghaoleixing(int zhanghaoleixing) {
                this.zhanghaoleixing = zhanghaoleixing;
            }

            public Object getYuangongsuoshubumen() {
                return yuangongsuoshubumen;
            }

            public void setYuangongsuoshubumen(Object yuangongsuoshubumen) {
                this.yuangongsuoshubumen = yuangongsuoshubumen;
            }

            public Object getYonghuvip() {
                return yonghuvip;
            }

            public void setYonghuvip(Object yonghuvip) {
                this.yonghuvip = yonghuvip;
            }

            public int getYonghujifen() {
                return yonghujifen;
            }

            public void setYonghujifen(int yonghujifen) {
                this.yonghujifen = yonghujifen;
            }

            public String getYonghushoujihao() {
                return yonghushoujihao;
            }

            public void setYonghushoujihao(String yonghushoujihao) {
                this.yonghushoujihao = yonghushoujihao;
            }

            public int getShifoushijihuoyonghu() {
                return shifoushijihuoyonghu;
            }

            public void setShifoushijihuoyonghu(int shifoushijihuoyonghu) {
                this.shifoushijihuoyonghu = shifoushijihuoyonghu;
            }

            public int getYonghubiaozhu() {
                return yonghubiaozhu;
            }

            public void setYonghubiaozhu(int yonghubiaozhu) {
                this.yonghubiaozhu = yonghubiaozhu;
            }

            public String getBeikao() {
                return beikao;
            }

            public void setBeikao(String beikao) {
                this.beikao = beikao;
            }

            public String getYonghutouxiang() {
                return yonghutouxiang;
            }

            public void setYonghutouxiang(String yonghutouxiang) {
                this.yonghutouxiang = yonghutouxiang;
            }

            public int getCongyeshijian() {
                return congyeshijian;
            }

            public void setCongyeshijian(int congyeshijian) {
                this.congyeshijian = congyeshijian;
            }

            public String getJiaoyimoshi() {
                return jiaoyimoshi;
            }

            public void setJiaoyimoshi(String jiaoyimoshi) {
                this.jiaoyimoshi = jiaoyimoshi;
            }

            public String getRenzhengbianhao() {
                return renzhengbianhao;
            }

            public void setRenzhengbianhao(String renzhengbianhao) {
                this.renzhengbianhao = renzhengbianhao;
            }

            public String getJiaoyifengge() {
                return jiaoyifengge;
            }

            public void setJiaoyifengge(String jiaoyifengge) {
                this.jiaoyifengge = jiaoyifengge;
            }

            public int getFensishuliang() {
                return fensishuliang;
            }

            public void setFensishuliang(int fensishuliang) {
                this.fensishuliang = fensishuliang;
            }

            public String getJinqizhanji() {
                return jinqizhanji;
            }

            public void setJinqizhanji(String jinqizhanji) {
                this.jinqizhanji = jinqizhanji;
            }

            public String getFenxishijieshaowangzhan() {
                return fenxishijieshaowangzhan;
            }

            public void setFenxishijieshaowangzhan(String fenxishijieshaowangzhan) {
                this.fenxishijieshaowangzhan = fenxishijieshaowangzhan;
            }

            public int getPingbifayanshijian() {
                return pingbifayanshijian;
            }

            public void setPingbifayanshijian(int pingbifayanshijian) {
                this.pingbifayanshijian = pingbifayanshijian;
            }

            public Object getPingbifayanliyou() {
                return pingbifayanliyou;
            }

            public void setPingbifayanliyou(Object pingbifayanliyou) {
                this.pingbifayanliyou = pingbifayanliyou;
            }

            public String getSirenzhiboshiid() {
                return sirenzhiboshiid;
            }

            public void setSirenzhiboshiid(String sirenzhiboshiid) {
                this.sirenzhiboshiid = sirenzhiboshiid;
            }

            public long getZhuceshijian() {
                return zhuceshijian;
            }

            public void setZhuceshijian(long zhuceshijian) {
                this.zhuceshijian = zhuceshijian;
            }

            public int getFangkequanxian() {
                return fangkequanxian;
            }

            public void setFangkequanxian(int fangkequanxian) {
                this.fangkequanxian = fangkequanxian;
            }

            public String getZhucepingtai() {
                return zhucepingtai;
            }

            public void setZhucepingtai(String zhucepingtai) {
                this.zhucepingtai = zhucepingtai;
            }
        }
    }
}
