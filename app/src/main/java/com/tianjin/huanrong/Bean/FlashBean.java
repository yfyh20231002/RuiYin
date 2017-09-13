package com.tianjin.huanrong.Bean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/23.
 */

public class FlashBean {

    /**
     * syscode : 0
     * msg :
     * data : {"businesscode":500,"dangtianshuju":[{"newsid":1008179,"newstype":null,"financeeffect":"","financeprediction":"","newsreferurl":"http://oil.cngold.com.cn/20170623d1816n158023259.html","newseffect":"","newsimportant":1,"financebefore":"","newsvideourl":"","financecountry":"","financename":"","financetime":-2209017600000,"istop":0,"newsimageurl":"http://img.cngold.com.cn/2017/6/23/20170623104504348727897.jpg","type":0,"reading":"","financerank":0,"newscontent":"【技术分析：美国原油可能重新测试42.08美元支撑位】周五（6月23日）亚市盘中，美国原油交投于42.84美元/桶附近。昨日，美国WTI8月原油期货收盘上涨0.25美元，涨幅0.59%，报42.78美元/桶。热带风暴袭击美油主产地或在短期内为油价提供支撑，但随着该风暴正在减弱，以及全球原油供给过剩的局面未改市场人气仍偏负面。","updatetime":1498185979000,"classname":"政经要闻","icon":"美国","tag":"","financeresult":"","id":256508}]}
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
         * businesscode : 500
         * dangtianshuju : [{"newsid":1008179,"newstype":null,"financeeffect":"","financeprediction":"","newsreferurl":"http://oil.cngold.com.cn/20170623d1816n158023259.html","newseffect":"","newsimportant":1,"financebefore":"","newsvideourl":"","financecountry":"","financename":"","financetime":-2209017600000,"istop":0,"newsimageurl":"http://img.cngold.com.cn/2017/6/23/20170623104504348727897.jpg","type":0,"reading":"","financerank":0,"newscontent":"【技术分析：美国原油可能重新测试42.08美元支撑位】周五（6月23日）亚市盘中，美国原油交投于42.84美元/桶附近。昨日，美国WTI8月原油期货收盘上涨0.25美元，涨幅0.59%，报42.78美元/桶。热带风暴袭击美油主产地或在短期内为油价提供支撑，但随着该风暴正在减弱，以及全球原油供给过剩的局面未改市场人气仍偏负面。","updatetime":1498185979000,"classname":"政经要闻","icon":"美国","tag":"","financeresult":"","id":256508}]
         */

        private int businesscode;
        private List<DangtianshujuBean> dangtianshuju;

        public int getBusinesscode() {
            return businesscode;
        }

        public void setBusinesscode(int businesscode) {
            this.businesscode = businesscode;
        }

        public List<DangtianshujuBean> getDangtianshuju() {
            return dangtianshuju;
        }

        public void setDangtianshuju(List<DangtianshujuBean> dangtianshuju) {
            this.dangtianshuju = dangtianshuju;
        }

        public static class DangtianshujuBean {
            /**
             * newsid : 1008179
             * newstype : null
             * financeeffect :
             * financeprediction :
             * newsreferurl : http://oil.cngold.com.cn/20170623d1816n158023259.html
             * newseffect :
             * newsimportant : 1
             * financebefore :
             * newsvideourl :
             * financecountry :
             * financename :
             * financetime : -2209017600000
             * istop : 0
             * newsimageurl : http://img.cngold.com.cn/2017/6/23/20170623104504348727897.jpg
             * type : 0
             * reading :
             * financerank : 0
             * newscontent : 【技术分析：美国原油可能重新测试42.08美元支撑位】周五（6月23日）亚市盘中，美国原油交投于42.84美元/桶附近。昨日，美国WTI8月原油期货收盘上涨0.25美元，涨幅0.59%，报42.78美元/桶。热带风暴袭击美油主产地或在短期内为油价提供支撑，但随着该风暴正在减弱，以及全球原油供给过剩的局面未改市场人气仍偏负面。
             * updatetime : 1498185979000
             * classname : 政经要闻
             * icon : 美国
             * tag :
             * financeresult :
             * id : 256508
             */

            private int newsid;
            private Object newstype;
            private String financeeffect;
            private String financeprediction;
            private String newsreferurl;
            private String newseffect;
            private int newsimportant;
            private String financebefore;
            private String newsvideourl;
            private String financecountry;
            private String financename;
            private long financetime;
            private int istop;
            private String newsimageurl;
            private int type;
            private String reading;
            private int financerank;
            private String newscontent;
            private long updatetime;
            private String classname;
            private String icon;
            private String tag;
            private String financeresult;
            private int id;

            public int getNewsid() {
                return newsid;
            }

            public void setNewsid(int newsid) {
                this.newsid = newsid;
            }

            public Object getNewstype() {
                return newstype;
            }

            public void setNewstype(Object newstype) {
                this.newstype = newstype;
            }

            public String getFinanceeffect() {
                return financeeffect;
            }

            public void setFinanceeffect(String financeeffect) {
                this.financeeffect = financeeffect;
            }

            public String getFinanceprediction() {
                return financeprediction;
            }

            public void setFinanceprediction(String financeprediction) {
                this.financeprediction = financeprediction;
            }

            public String getNewsreferurl() {
                return newsreferurl;
            }

            public void setNewsreferurl(String newsreferurl) {
                this.newsreferurl = newsreferurl;
            }

            public String getNewseffect() {
                return newseffect;
            }

            public void setNewseffect(String newseffect) {
                this.newseffect = newseffect;
            }

            public int getNewsimportant() {
                return newsimportant;
            }

            public void setNewsimportant(int newsimportant) {
                this.newsimportant = newsimportant;
            }

            public String getFinancebefore() {
                return financebefore;
            }

            public void setFinancebefore(String financebefore) {
                this.financebefore = financebefore;
            }

            public String getNewsvideourl() {
                return newsvideourl;
            }

            public void setNewsvideourl(String newsvideourl) {
                this.newsvideourl = newsvideourl;
            }

            public String getFinancecountry() {
                return financecountry;
            }

            public void setFinancecountry(String financecountry) {
                this.financecountry = financecountry;
            }

            public String getFinancename() {
                return financename;
            }

            public void setFinancename(String financename) {
                this.financename = financename;
            }

            public long getFinancetime() {
                return financetime;
            }

            public void setFinancetime(long financetime) {
                this.financetime = financetime;
            }

            public int getIstop() {
                return istop;
            }

            public void setIstop(int istop) {
                this.istop = istop;
            }

            public String getNewsimageurl() {
                return newsimageurl;
            }

            public void setNewsimageurl(String newsimageurl) {
                this.newsimageurl = newsimageurl;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getReading() {
                return reading;
            }

            public void setReading(String reading) {
                this.reading = reading;
            }

            public int getFinancerank() {
                return financerank;
            }

            public void setFinancerank(int financerank) {
                this.financerank = financerank;
            }

            public String getNewscontent() {
                return newscontent;
            }

            public void setNewscontent(String newscontent) {
                this.newscontent = newscontent;
            }

            public long getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(long updatetime) {
                this.updatetime = updatetime;
            }

            public String getClassname() {
                return classname;
            }

            public void setClassname(String classname) {
                this.classname = classname;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getFinanceresult() {
                return financeresult;
            }

            public void setFinanceresult(String financeresult) {
                this.financeresult = financeresult;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
