package com.micro.boot.app.object.response.topic;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈MC设备 （此表只记录公共参数）〉
 *
 * @author Administrator
 * @create 2018/3/25
 * @since 1.0.0
 */
public class McTopicRep implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //主题名称
    private String top_name;

    //消息数
    private String top_messages;
    //生产数
    private String num_producers;
    //消费数
    private String num_consumers;

    //单位bytes：生产数据大小
    private String size_producers;
    //单位bytes：消费数据大小
    private String size_consumers;

    //mc_user_id
    private Long user_id;
    //创建时间
    private Date createTime;
    //状态  0：禁用   1：正常
    private Integer status;

}