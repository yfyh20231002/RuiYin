package com.unitesoft.huanrong.markcenter.ziye;


/**
 * Created by naive on 2017/4/17.
 */
public class
Bean {
        private int id;
        private int sort;
        private String Fullname;
        private String Shortname;

        private String url;
        private String port;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFullname() {
            return Fullname;
        }

        public void setFullname(String fullname) {
            Fullname = fullname;
        }

        public String getShortname() {
            return Shortname;
        }

        public void setShortname(String shortname) {
            Shortname = shortname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

    }



