package com.example.shichang393.ruiyin.Bean;

import java.util.List;

/**
 * Created by Mr.zhang on 2017/7/31.
 */

public class TeacherBean {
    /**
     * syscode : 0
     * msg :
     * data : {"businesscode":300,"analysts":[]}
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
         * analysts : []
         */

        private int businesscode;
        private List<?> analysts;

        public int getBusinesscode() {
            return businesscode;
        }

        public void setBusinesscode(int businesscode) {
            this.businesscode = businesscode;
        }

        public List<?> getAnalysts() {
            return analysts;
        }

        public void setAnalysts(List<?> analysts) {
            this.analysts = analysts;
        }
    }
}
