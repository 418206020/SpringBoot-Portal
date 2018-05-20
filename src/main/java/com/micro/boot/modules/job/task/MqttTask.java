package com.micro.boot.modules.job.task;

import com.micro.boot.app.service.queue.McTopicService;
import com.micro.boot.common.Constants;
import com.micro.boot.thirdparty.paho.MQTTClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    //间隔5秒
    private static final long INTERVAL = 30 * 1000;

    @Resource
    private McTopicService mcTopicService;

    @Resource
    private MQTTClient mqttClient;

    /**
     * 监听服务
     *
     * @param params
     */
    public void listener(String params) {
        final long timeInterval = INTERVAL;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    try {
                        try {
                            String[] topics = {Constants.M2M + "#"};
                            String clientID = UUID.randomUUID().toString();
                            mqttClient.subscribe(clientID, topics);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                        Thread.sleep(5); //接收服务器消息
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
