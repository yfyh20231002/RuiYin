package com.tianjin.huanrong.Bean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/6/27.
 */

public class BannerBean {
    /**
     * syscode : 0
     * msg :
     * news : [{"typeNo":"1","title":"斗金","picture":"http://zb.006006.cn:10001/appsv/templates/public/image/lb1.png","picturecontent":"http://zb.006006.cn:10001/appsv/templates/public/image/lbcontent1.png"},{"typeNo":"1","title":"斗金","picture":"http://zb.006006.cn:10001/appsv/templates/public/image/lb2.png","picturecontent":"http://zb.006006.cn:10001/appsv/templates/public/image/lbcontent2.png"},{"typeNo":"1","title":"斗金","picture":"http://zb.006006.cn:10001/appsv/templates/public/image/lb3.png","picturecontent":"http://zb.006006.cn:10001/appsv/templates/public/image/lbcontent3.png"},{"typeNo":"1","title":"斗金","picture":"http://zb.006006.cn:10001/appsv/templates/public/image/lb4.png","picturecontent":"http://zb.006006.cn:10001/appsv/templates/public/image/lbcontent4.png"}]
     * timestamp : 1498543347415
     */

    private int syscode;
    private String msg;
    private long timestamp;
    private List<NewsBean> news;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public static class NewsBean {
        /**
         * typeNo : 1
         * title : 斗金
         * picture : http://zb.006006.cn:10001/appsv/templates/public/image/lb1.png
         * picturecontent : http://zb.006006.cn:10001/appsv/templates/public/image/lbcontent1.png
         */

        private String typeNo;
        private String title;
        private String picture;
        private String picturecontent;

        public String getTypeNo() {
            return typeNo;
        }

        public void setTypeNo(String typeNo) {
            this.typeNo = typeNo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getPicturecontent() {
            return picturecontent;
        }

        public void setPicturecontent(String picturecontent) {
            this.picturecontent = picturecontent;
        }
    }
}
