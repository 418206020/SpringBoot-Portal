package com.micro.boot.app.service.queue.impl;

import com.micro.boot.app.dao.McTopicDao;
import com.micro.boot.app.object.MQTTSubscriber;
import com.micro.boot.app.service.queue.McTopicService;
import com.micro.boot.common.Constants;
import com.micro.boot.common.utils.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
@Service
@Transactional
public class McTopicServiceImpl implements McTopicService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private McTopicDao mcTopicDao;

    /**
     * 返回注册用户所有设备MAC作为主题订阅
     * @param mobile
     *
     * @return
     */
    @Override public String[] getTopicByMobile(String mobile) {
        MQTTSubscriber request = new MQTTSubscriber();
        request.setMobile(mobile);
        List<MQTTSubscriber> mqttSubscriberList = mcTopicDao.listTopicByMobile(request);
        String[] topics = new String[mqttSubscriberList.size()];
        for (int k = 0; k < mqttSubscriberList.size(); k++) {
            topics[k] = Constants.M2M + mqttSubscriberList.get(k).getDevMacid();
        }
        return topics;
    }

    @Override public String[] getTopicByClient(String clientId) {
        return getTopicByMobile(clientId);
    }
}