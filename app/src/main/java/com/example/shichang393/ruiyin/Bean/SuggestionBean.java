package com.example.shichang393.ruiyin.Bean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/10.
 */

public class SuggestionBean {
    /**
     * syscode : 0
     * msg :
     * data : {"businesscode":400,"LIVEROOMSSTICK":[{"zhidingxiaoxiid":"04131C59497E403DAB42BBFA2E8E3E1B","zhidingren":"DAE20C728DF145FA9B331922929823EE","fenxishixingming":"李亚蓓","fenxishitouxiang":"/upload/kehuziliaoguanli/201601/1452567336927.png","zhidingshijian":"2017-07-10 09:41:14","shuohuaneirong":"白银继续走弱，还是以高空为主，但是做空是短线，毕竟价格低，3389现价做空的话，下方暂时看3330--3340 ，止损3420 （以上建议 仅供参考）","liaotianjiluid":"B80A038E15394620BB1B8B3ABA7EF761","zhiboshimingcheng":"天涯海阁"},{"zhidingxiaoxiid":"62041FE90E254D10B86422D8AD04ACD1","zhidingren":"DAE20C728DF145FA9B331922929823EE","fenxishixingming":"李亚蓓","fenxishitouxiang":"/upload/kehuziliaoguanli/201601/1452567336927.png","zhidingshijian":"2017-07-10 09:41:14","shuohuaneirong":"柏油上周维持下行格局，咱们的空单应该 是盈利很多，不知道大家做的怎么样， 周五柏油轻微反弹，但幅度很弱，预计下行还未结束，日线明显的单边下行还未走完，继续逢高沽空：建议 2285-2290区间沽空，目标2220、2155，止损2320 ；（以上建议 仅供参考）","liaotianjiluid":"F30CFBC9BD5849F6B728110E722F154B","zhiboshimingcheng":"天涯海阁"},{"zhidingxiaoxiid":"4372F2DD94294607B83B8A090B07212A","zhidingren":"DAE20C728DF145FA9B331922929823EE","fenxishixingming":"李亚蓓","fenxishitouxiang":"/upload/kehuziliaoguanli/201601/1452567336927.png","zhidingshijian":"2017-07-10 09:41:15","shuohuaneirong":"铜整体的下行幅度暂缓，不过目前来看空头为主力力量，因此 还是建议高空思路，只是 找高位做，以免被反弹攻击；39770--39820区间沽空，目标39450、39250，止损40000（以上建议 仅供参考）","liaotianjiluid":"3CF59D73C20D49DEB9A6311489E130E7","zhiboshimingcheng":"天涯海阁"}]}
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
         * LIVEROOMSSTICK : [{"zhidingxiaoxiid":"04131C59497E403DAB42BBFA2E8E3E1B","zhidingren":"DAE20C728DF145FA9B331922929823EE","fenxishixingming":"李亚蓓","fenxishitouxiang":"/upload/kehuziliaoguanli/201601/1452567336927.png","zhidingshijian":"2017-07-10 09:41:14","shuohuaneirong":"白银继续走弱，还是以高空为主，但是做空是短线，毕竟价格低，3389现价做空的话，下方暂时看3330--3340 ，止损3420 （以上建议 仅供参考）","liaotianjiluid":"B80A038E15394620BB1B8B3ABA7EF761","zhiboshimingcheng":"天涯海阁"},{"zhidingxiaoxiid":"62041FE90E254D10B86422D8AD04ACD1","zhidingren":"DAE20C728DF145FA9B331922929823EE","fenxishixingming":"李亚蓓","fenxishitouxiang":"/upload/kehuziliaoguanli/201601/1452567336927.png","zhidingshijian":"2017-07-10 09:41:14","shuohuaneirong":"柏油上周维持下行格局，咱们的空单应该 是盈利很多，不知道大家做的怎么样， 周五柏油轻微反弹，但幅度很弱，预计下行还未结束，日线明显的单边下行还未走完，继续逢高沽空：建议 2285-2290区间沽空，目标2220、2155，止损2320 ；（以上建议 仅供参考）","liaotianjiluid":"F30CFBC9BD5849F6B728110E722F154B","zhiboshimingcheng":"天涯海阁"},{"zhidingxiaoxiid":"4372F2DD94294607B83B8A090B07212A","zhidingren":"DAE20C728DF145FA9B331922929823EE","fenxishixingming":"李亚蓓","fenxishitouxiang":"/upload/kehuziliaoguanli/201601/1452567336927.png","zhidingshijian":"2017-07-10 09:41:15","shuohuaneirong":"铜整体的下行幅度暂缓，不过目前来看空头为主力力量，因此 还是建议高空思路，只是 找高位做，以免被反弹攻击；39770--39820区间沽空，目标39450、39250，止损40000（以上建议 仅供参考）","liaotianjiluid":"3CF59D73C20D49DEB9A6311489E130E7","zhiboshimingcheng":"天涯海阁"}]
         */

        private int businesscode;
        private List<LIVEROOMSSTICKBean> LIVEROOMSSTICK;

        public int getBusinesscode() {
            return businesscode;
        }

        public void setBusinesscode(int businesscode) {
            this.businesscode = businesscode;
        }

        public List<LIVEROOMSSTICKBean> getLIVEROOMSSTICK() {
            return LIVEROOMSSTICK;
        }

        public void setLIVEROOMSSTICK(List<LIVEROOMSSTICKBean> LIVEROOMSSTICK) {
            this.LIVEROOMSSTICK = LIVEROOMSSTICK;
        }

        public static class LIVEROOMSSTICKBean {
            /**
             * zhidingxiaoxiid : 04131C59497E403DAB42BBFA2E8E3E1B
             * zhidingren : DAE20C728DF145FA9B331922929823EE
             * fenxishixingming : 李亚蓓
             * fenxishitouxiang : /upload/kehuziliaoguanli/201601/1452567336927.png
             * zhidingshijian : 2017-07-10 09:41:14
             * shuohuaneirong : 白银继续走弱，还是以高空为主，但是做空是短线，毕竟价格低，3389现价做空的话，下方暂时看3330--3340 ，止损3420 （以上建议 仅供参考）
             * liaotianjiluid : B80A038E15394620BB1B8B3ABA7EF761
             * zhiboshimingcheng : 天涯海阁
             */

            private String zhidingxiaoxiid;
            private String zhidingren;
            private String fenxishixingming;
            private String fenxishitouxiang;
            private String zhidingshijian;
            private String shuohuaneirong;
            private String liaotianjiluid;
            private String zhiboshimingcheng;

            public String getZhidingxiaoxiid() {
                return zhidingxiaoxiid;
            }

            public void setZhidingxiaoxiid(String zhidingxiaoxiid) {
                this.zhidingxiaoxiid = zhidingxiaoxiid;
            }

            public String getZhidingren() {
                return zhidingren;
            }

            public void setZhidingren(String zhidingren) {
                this.zhidingren = zhidingren;
            }

            public String getFenxishixingming() {
                return fenxishixingming;
            }

            public void setFenxishixingming(String fenxishixingming) {
                this.fenxishixingming = fenxishixingming;
            }

            public String getFenxishitouxiang() {
                return fenxishitouxiang;
            }

            public void setFenxishitouxiang(String fenxishitouxiang) {
                this.fenxishitouxiang = fenxishitouxiang;
            }

            public String getZhidingshijian() {
                return zhidingshijian;
            }

            public void setZhidingshijian(String zhidingshijian) {
                this.zhidingshijian = zhidingshijian;
            }

            public String getShuohuaneirong() {
                return shuohuaneirong;
            }

            public void setShuohuaneirong(String shuohuaneirong) {
                this.shuohuaneirong = shuohuaneirong;
            }

            public String getLiaotianjiluid() {
                return liaotianjiluid;
            }

            public void setLiaotianjiluid(String liaotianjiluid) {
                this.liaotianjiluid = liaotianjiluid;
            }

            public String getZhiboshimingcheng() {
                return zhiboshimingcheng;
            }

            public void setZhiboshimingcheng(String zhiboshimingcheng) {
                this.zhiboshimingcheng = zhiboshimingcheng;
            }
        }
    }
}
