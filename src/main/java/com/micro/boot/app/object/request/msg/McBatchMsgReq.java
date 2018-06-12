package com.micro.boot.app.object.request.msg;

import java.io.Serializable;
import java.util.Date;

/**
 * McMsgReq〉
 *
 * @author Administrator
 * @create 2018/3/25
 * @since 1.0.0
 */
public class McBatchMsgReq implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分页SQL固定
     * offset
     * limit
     */
    private Integer offset;
    private Integer limit;
    //排序
    private String orderString;


    //id
    private Long id;
    //所属topic
    private Long topId;

    private Long userid;

    private String devid;//特殊处理

    //扩展字段：手机、主题名称（设备macid）、消息
    private String mobile;
    private String topicName;
    private String message;

    //消息分类
    private String msgType;
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

    /**
     * 公共方法
     */
    public void setPage(Integer pageNo, Integer pageSize) {
        this.setOffset(pageSize * (pageNo - 1));
        this.setLimit(pageSize * pageNo);
    }

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

    public String getOrderString() {
        return orderString;
    }

    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
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

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
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