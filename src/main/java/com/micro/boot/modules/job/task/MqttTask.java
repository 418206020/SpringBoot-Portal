package com.micro.boot.modules.job.task;

import com.micro.boot.app.service.queue.McTopicService;
import com.micro.boot.modules.sys.entity.SysUserEntity;
import com.micro.boot.modules.sys.service.SysUserService;
import com.micro.boot.thirdparty.paho.ClientSearch;
import net.sf.json.JSONObject;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
//
//    private static final String KEY_CLIENT = "clientId";
//    private static final String KEY_TOPICS = "topics";

    @Resource
    private McTopicService mcTopicService;

    public void listener(String clientId) {
        final long timeInterval = 5 * 1000;// 5分钟运行一次

//        JSONObject jsonObject = JSONObject.fromObject(params);
//        String clientId = jsonObject.getString("KEY_CLIENT");
        String[] topics = mcTopicService.getTopicByClient(clientId);

        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    try {
                        ClientSearch clientSearch = new ClientSearch();
                        try {
                            clientSearch.subscribe(clientId, topics);
                        } catch (MqttException e) {
                            logger.error(e.getMessage());
                        }
                        Thread.sleep(1); //给ms时间接收服务器消息
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage());
                    }
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage());
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
