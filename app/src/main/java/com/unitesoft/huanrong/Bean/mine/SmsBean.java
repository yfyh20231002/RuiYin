package com.unitesoft.huanrong.Bean.mine;

/**
 * Created by Mr.zhang on 2017/9/18.
 */

public class SmsBean {

    /**
     * totalCount : 1
     * message : 200
     * success : 0
     * result : 0
     * flag : 发送成功！
     * validateNum : 525584
     */

    private String totalCount;
    private String message;
    private String success;
    private String result;
    private String flag;
    private String validateNum;
    private String phone;
    private String bm;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getValidateNum() {
        return validateNum;
    }

    public void setValidateNum(String validateNum) {
        this.validateNum = validateNum;
    }
}
