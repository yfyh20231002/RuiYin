package com.example.shichang393.ruiyin.Bean.mine;

/**
 * Created by Mr.zhang on 2017/8/11.
 */

public class NickNamePostBean {
    private String userPhone;
    private String nickName;

    public NickNamePostBean(String userPhone, String nickName) {
        this.userPhone = userPhone;
        this.nickName = nickName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
