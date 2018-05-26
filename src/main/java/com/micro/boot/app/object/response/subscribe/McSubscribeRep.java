package com.micro.boot.app.object.response.subscribe;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈MC设备 （此表只记录公共参数）〉
 *
 * @author Administrator
 * @create 2018/3/25
 * @since 1.0.0
 */
public class McSubscribeRep implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;

    //订阅用户
    private Long subUserId;

    //订阅主题
    private Long subTopicId;

    private String topics;
    private String clientid;

    //状态  0：禁用   1：正常
    private String status;

    //创建时间
    private Date subTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubUserId() {
        return subUserId;
    }

    public void setSubUserId(Long subUserId) {
        this.subUserId = subUserId;
    }

    public Long getSubTopicId() {
        return subTopicId;
    }

    public void setSubTopicId(Long subTopicId) {
        this.subTopicId = subTopicId;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSubTime() {
        return subTime;
    }

    public void setSubTime(Date subTime) {
        this.subTime = subTime;
    }
}