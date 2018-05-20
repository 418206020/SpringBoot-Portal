package com.micro.boot.app.dao;

import com.micro.boot.app.object.MQTTSubscriber;
import com.micro.boot.app.object.request.topic.McTopicReq;
import com.micro.boot.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-10-23 21:23:54
 */
@Mapper
public interface McTopicDao extends BaseDao<McTopicReq> {

    List<MQTTSubscriber> listTopicByMobile(MQTTSubscriber mqttSubscriber);

}