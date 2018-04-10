package com.micro.boot.app.object.request.topic;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈MC设备 （此表只记录公共参数）〉
 *
 * @author Administrator
 * @create 2018/3/25
 * @since 1.0.0
 */
public class McTopicReq implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //主题名称
    private String name;
    //消息数
    private String messages;
    //生产数
    private String numProducers;
    //消费数
    private String numConsumers;
    //单位bytes：生产数据大小
    private String sizeProducers;
    //单位bytes：消费数据大小
    private String sizeConsumers;
    //mc_user_id
    private Long userId;
    //创建时间
    private Date createTime;
    //状态  0：禁用   1：正常
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getNumProducers() {
        return numProducers;
    }

    public void setNumProducers(String numProducers) {
        this.numProducers = numProducers;
    }

    public String getNumConsumers() {
        return numConsumers;
    }

    public void setNumConsumers(String numConsumers) {
        this.numConsumers = numConsumers;
    }

    public String getSizeProducers() {
        return sizeProducers;
    }

    public void setSizeProducers(String sizeProducers) {
        this.sizeProducers = sizeProducers;
    }

    public String getSizeConsumers() {
        return sizeConsumers;
    }

    public void setSizeConsumers(String sizeConsumers) {
        this.sizeConsumers = sizeConsumers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}