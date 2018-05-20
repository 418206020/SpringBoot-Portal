package com.micro.boot.thirdparty.paho;

import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.app.service.message.McMessageService;
import com.micro.boot.app.service.queue.McTopicService;
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

    @Resource
    private McMessageService mcMessageService;

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
        client = new MqttClient(HOST, UUID.randomUUID().toString(), new MemoryPersistence());
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
                System.out.println("connectionLost, retry...");
            }

            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete---------" + token.isComplete());
            }

            public void messageArrived(String topic, MqttMessage message) throws Exception {
                try {
                    String msg = message.toString();
                    logger.info(topic + msg);
                    DeviceStateRep rep = AnalyticUtil.analytic(msg);
                    mcMessageService.saveMQTT(topic, msg);
                    logger.info("DeviceStateRep:" + rep.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}