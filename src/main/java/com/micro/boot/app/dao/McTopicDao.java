package com.micro.boot.app.dao;

import com.micro.boot.app.object.MQTTSubscriber;
import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.app.object.request.topic.McTopicReq;
import com.micro.boot.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;

/**
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-10-23 21:23:54
 */
@Mapper
public interface McTopicDao extends BaseDao<McTopicReq> {

    /**
     * @param mqttSubscriber
     *
     * @return
     */
    List<MQTTSubscriber> listTopicByMobile(MQTTSubscriber mqttSubscriber);

    /**
     * 批量更新所属用户
     *
     * @return
     */
    int updateMqttUser();

    /**
     * 批量更新所属设备
     *
     * @return
     */
    int updateMqttDevice();

    /**
     * 更新 所属用户
     *
     * @return
     */
    int updateUserByMsg(String msgId);

    /**
     * 更新 所属设备
     *
     * @return
     */
    int updateDeviceByMsg(String msgId);

    /**
     * @return
     */
    int deleteHistory(McMsgReq req);

}