package com.micro.boot.app.service.queue.impl;

import com.micro.boot.app.dao.McSubscribeDao;
import com.micro.boot.app.dao.McTopicDao;
import com.micro.boot.app.object.MQTTSubscriber;
import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.app.object.request.subscribe.McSubscribeReq;
import com.micro.boot.app.object.response.subscribe.McSubscribeRep;
import com.micro.boot.app.service.queue.McSubscriberService;
import com.micro.boot.app.service.queue.McTopicService;
import com.micro.boot.common.Constants;
import com.micro.boot.common.utils.DateUtils;
import com.micro.boot.common.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class McSubscriberServiceImpl implements McSubscriberService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private McSubscribeDao mcSubscribeDao;

    /**
     * 订阅记录
     *
     * @param clientId
     * @param topics
     */
    @Override
    public void subscriber(String clientId, String[] topics) {
        McSubscribeReq request = new McSubscribeReq();
        request.setStatus(String.valueOf(Constants.ONE));//启动订阅
        request.setSubTime(new Date());
        request.setClientid(clientId);
        request.setTopics(topics[0]);//以**分隔符
        mcSubscribeDao.addSubscriber(request);
        logger.info("subscriber addSubscriber:" + clientId);
    }

    /**
     * 取消所有订阅
     */
    @Override public void unsubscribe() {
        mcSubscribeDao.unsubscribe();
        logger.info("unsubscribe:");
    }

    @Override public List<McSubscribeRep> listSubscriber(McSubscribeReq request) {
        logger.info("listSubscriber:");
        return mcSubscribeDao.listSubsriber(request);
    }
}