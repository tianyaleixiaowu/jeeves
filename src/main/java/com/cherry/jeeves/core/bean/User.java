package com.cherry.jeeves.core.bean;


/**
 * @author wuweifeng wrote on 2018/4/30.
 */
public class User {
    //@3cbbfe187b8fe9681f55d71765ab25dee6e7096dfc4f00f5108ebe9dcbb0aed1
    private String UserName;
    /**
     * 用户昵称
     */
    private String NickName;
    private String HeadImgUrl;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getHeadImgUrl() {
        return HeadImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        HeadImgUrl = headImgUrl;
    }
}
