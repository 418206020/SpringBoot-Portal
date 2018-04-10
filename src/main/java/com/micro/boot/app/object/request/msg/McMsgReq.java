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
public class McMsgReq implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //所属topic
    private Long top_id;
    //消息分类
    private Long msg_type;
    //消息报文
    private String request_qos;
    //返回报文
    private String suback;
    //单位：bytes
    private String msg_size;
    //生成时间
    private Date time_producer;
    //消费时间
    private Date time_consumer;
    //状态  0：消费   1：生产
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTop_id() {
        return top_id;
    }

    public void setTop_id(Long top_id) {
        this.top_id = top_id;
    }

    public Long getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(Long msg_type) {
        this.msg_type = msg_type;
    }

    public String getRequest_qos() {
        return request_qos;
    }

    public void setRequest_qos(String request_qos) {
        this.request_qos = request_qos;
    }

    public String getSuback() {
        return suback;
    }

    public void setSuback(String suback) {
        this.suback = suback;
    }

    public String getMsg_size() {
        return msg_size;
    }

    public void setMsg_size(String msg_size) {
        this.msg_size = msg_size;
    }

    public Date getTime_producer() {
        return time_producer;
    }

    public void setTime_producer(Date time_producer) {
        this.time_producer = time_producer;
    }

    public Date getTime_consumer() {
        return time_consumer;
    }

    public void setTime_consumer(Date time_consumer) {
        this.time_consumer = time_consumer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}