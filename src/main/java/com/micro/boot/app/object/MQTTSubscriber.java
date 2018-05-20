package com.micro.boot.app.object;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2018/5/20
 * @since 1.0.0
 */
public class MQTTSubscriber {

    private String userId;
    private String mobile;
    private String devMacid;

    private String clientId;
    private String[] topics;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDevMacid() {
        return devMacid;
    }

    public void setDevMacid(String devMacid) {
        this.devMacid = devMacid;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String[] getTopics() {
        return topics;
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
    }
}