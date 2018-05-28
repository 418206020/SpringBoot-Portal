package com.micro.boot.thirdparty.paho;

import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.app.service.message.McMessageService;
import com.micro.boot.app.service.queue.McSubscriberService;
import com.micro.boot.app.service.queue.McTopicService;
import com.micro.boot.modules.job.service.ScheduleJobService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
/**
 * 〈〉
 *
 * @author Administrator
 * @create 2018/5/19
 * @since 1.0.0
 */

/**
 * 模拟一个客户端接收消息
 *
 * @author rao
 */
@Component
public class MQTTClient {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 配置
     */
    //    private static String serverURL = "tcp://127.0.0.1:61613";
    public static final String HOST = "tcp://mqtt.mym2mcloud.com:61613";
    private MqttClient client;
    private MqttConnectOptions options;
    private String userName = "admin";
    private String passWord = "password";
    private long jobId = 3; //立即执行任务

    @Resource
    private McMessageService mcMessageService;

    @Resource
    private McSubscriberService mcSubscriberService;
    @Resource
    private ScheduleJobService scheduleJobService;

    /**
     * 指定客户端订阅主题
     *
     * @param mobile 手机号做客户端ID
     * @param topics 主题
     *
     * @throws MqttException
     */
    public void subscribe(String mobile, String[] topics) throws MqttException {

        // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, mobile, new MemoryPersistence());
        // MQTT的连接设置
        options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(false);
        // 设置连接的用户名
        options.setUserName(userName);
        // 设置连接的密码
        options.setPassword(passWord.toCharArray());

        client.connect(options);
        //默认Qos=1
        client.subscribe(topics);

        // 设置回调
        client.setCallback(new MqttCallback() {
            public void connectionLost(Throwable cause) {
                System.out.println("connectionLost, ");
                logger.info("unsubscribe history..");
                mcSubscriberService.unsubscribe();//取消
                //重新订阅
                String clientRetry = String.valueOf(System.currentTimeMillis());
                mcSubscriberService.subscriber(clientRetry, topics);
                //MQTT重连
                Long[] jobs = new Long[1];
                jobs[0] = jobId;
                scheduleJobService.run(jobs);
                logger.info("subscribe new topics by client " + clientRetry + "..");
            }

            public void deliveryComplete(IMqttDeliveryToken token) {
            }

            public void messageArrived(String topic, MqttMessage message) throws Exception {
                try {
                    String msg = message.toString();
                    logger.info(topic + msg);
                    mcMessageService.saveMQTT(mobile, topic, msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 指定客户端订阅主题
     *
     * @param clientId 手机号做客户端ID
     * @param topics   主题
     *
     * @throws MqttException
     */
    public void unsubscribe(String clientId, String[] topics) throws MqttException {

        // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientId, new MemoryPersistence());
        // MQTT的连接设置
        options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(false);
        // 设置连接的用户名
        options.setUserName(userName);
        // 设置连接的密码
        options.setPassword(passWord.toCharArray());

        client.connect(options);
        //默认Qos=1
        client.unsubscribe(topics);
    }

}