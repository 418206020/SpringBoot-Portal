package com.micro.boot.modules.job.task;

import com.micro.boot.app.dao.McSubscribeDao;
import com.micro.boot.app.object.request.subscribe.McSubscribeReq;
import com.micro.boot.app.object.response.subscribe.McSubscribeRep;
import com.micro.boot.app.service.queue.McSubscriberService;
import com.micro.boot.app.service.queue.McTopicService;
import com.micro.boot.common.Constants;
import com.micro.boot.thirdparty.paho.MQTTClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 测试定时任务(演示Demo，可删除)
 * <p>
 * testTask为spring bean的名称
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2016年11月30日 下午1:34:24
 */
@Component("mqttTask")
public class MqttTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //间隔
    private static final long INTERVAL = 30 * 1000;

    @Resource
    private McTopicService mcTopicService;

    @Resource
    private McSubscriberService mcSubscriberService;

    @Resource
    private MQTTClient mqttClient;

    /**
     * 监听服务
     *
     * @param params
     */
    public void listener(String params) {
        Runnable runnable = new Runnable() {
            public void run() {
                String[] topics = {Constants.M2M + "#"};
                String clientID = String.valueOf(System.currentTimeMillis());
                try {
                    try {
                        logger.info("subscribe:" + clientID);
                        mqttClient.subscribe(clientID, topics);
                        mcSubscriberService.subscriber(clientID, topics);
                    } catch (MqttException e) {
                        logger.info(e.getMessage());
                    }
                    Thread.sleep(5); //接收服务器消息
                } catch (InterruptedException e) {
                    logger.info(e.getMessage());
                }
//                finally {
//                    try {
//                        logger.info("unsubscribe:" + clientID);
//                        mqttClient.unsubscribe(clientID, topics);
//                    } catch (MqttException e) {
//                        logger.info(e.getMessage());
//                    }
//                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     *
     */
    public void update() {
        mcTopicService.updateNullDeviceId();
        mcTopicService.updateNullUserId();
    }

    /**
     *
     */
    public void history(String minutes) {
        try {
            int min = Integer.parseInt(minutes);
            mcTopicService.deleteHistory(min);
        }catch (Exception e){

        }
    }

    /**
     * 取消历史订阅
     */
    public void unSubscriber() throws MqttException {
        McSubscribeReq request = new McSubscribeReq();
        request.setStatus(String.valueOf(Constants.ONE));
        List<McSubscribeRep> unscriberList = mcSubscriberService.listSubscriber(request);
        //循环取消client的订阅
        for (McSubscribeRep rep : unscriberList) {
            mqttClient.unsubscribe(rep.getClientid(), new String[]{rep.getTopics()});
        }
        mcSubscriberService.unsubscribe();
    }
}
