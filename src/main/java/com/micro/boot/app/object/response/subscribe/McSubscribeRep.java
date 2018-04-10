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
    private Long sub_user_id;

    //订阅主题
    private Long sub_topic_id;

    //状态  0：禁用   1：正常
    private String status;

    //创建时间
    private Date sub_time;

}