package com.micro.boot.app.object.response.msg;

import java.io.Serializable;
import java.util.Date;

/**
 * McMsgReq〉
 *
 * @author Administrator
 * @create 2018/3/25
 * @since 1.0.0
 */
public class McMsgRep implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //所属topic
    private Long topId;
    private Long userid;
    private Long devid;
    //扩展字段：手机、主题名称（设备macid）、消息
    private String mobile;
    private String topicName;
    private String message;
    //消息分类
    private Long msgType;
    //消息报文
    private String requestQos;
    //返回报文
    private String suback;
    //单位：bytes
    private String msgSize;
    //生成时间
    private Date timeProducer;
    //消费时间
    private Date timeConsumer;
    //状态  0：消费   1：生产
    private Integer status;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getDevid() {
        return devid;
    }

    public void setDevid(Long devid) {
        this.devid = devid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTopId() {
        return topId;
    }

    public void setTopId(Long topId) {
        this.topId = topId;
    }

    public Long getMsgType() {
        return msgType;
    }

    public void setMsgType(Long msgType) {
        this.msgType = msgType;
    }

    public String getRequestQos() {
        return requestQos;
    }

    public void setRequestQos(String requestQos) {
        this.requestQos = requestQos;
    }

    public String getSuback() {
        return suback;
    }

    public void setSuback(String suback) {
        this.suback = suback;
    }

    public String getMsgSize() {
        return msgSize;
    }

    public void setMsgSize(String msgSize) {
        this.msgSize = msgSize;
    }

    public Date getTimeProducer() {
        return timeProducer;
    }

    public void setTimeProducer(Date timeProducer) {
        this.timeProducer = timeProducer;
    }

    public Date getTimeConsumer() {
        return timeConsumer;
    }

    public void setTimeConsumer(Date timeConsumer) {
        this.timeConsumer = timeConsumer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}