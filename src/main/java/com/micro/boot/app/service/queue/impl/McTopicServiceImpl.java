package com.micro.boot.app.service.queue.impl;

import com.micro.boot.app.dao.McTopicDao;
import com.micro.boot.app.object.MQTTSubscriber;
import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.app.service.queue.McTopicService;
import com.micro.boot.common.Constants;
import com.micro.boot.common.utils.DateUtils;
import com.micro.boot.common.utils.RedisUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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
     *
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

    /**
     * @param clientId
     *
     * @return
     */
    @Override public String[] getTopicByClient(String clientId) {
        return getTopicByMobile(clientId);
    }

    /**
     * 更新
     *
     * @param
     */
    @Override public void updateNullUserId() {
        mcTopicDao.updateMqttUser();
    }

    /**
     * 更新
     *
     * @param
     */
    @Override public void updateNullDeviceId() {
        mcTopicDao.updateMqttDevice();
    }

    /**
     * 每天23:23:59清空当天数据之前
     * 删除历史
     *
     * @param
     */
    @Override public void deleteHistory(int minutes) {
        //删除一天之前的数据
        Date beforeDate = DateUtils.getMinutesBefore(minutes, new Date(System.currentTimeMillis()));
        McMsgReq req = new McMsgReq();
        req.setTimeConsumer(DateUtils.getSqlDateByUtilDate(beforeDate));
        mcTopicDao.deleteHistory(req);
    }
}