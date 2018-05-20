package com.micro.boot.thirdparty.paho;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
/**
 * 〈〉
 *
 * @author Administrator
 * @create 2018/5/19
 * @since 1.0.0
 */
/**
 * 模拟一个客户端接收消息
 * @author rao
 *
 */

public class MQTTClient {
    public static String str="2";
    private static String serverURL = "tcp://mqtt.mym2mcloud.com:61613";
//    private static String serverURL = "tcp://127.0.0.1:61613";
    public static final String HOST = serverURL;
    //服务器内置主题，用来监测当前服务器上连接的客户端数量（$SYS/broker/clients/connected）
    public static final String TOPIC1 = "/m2m/"+"1234567890000000";
    private static final String clientid = "1348812079202555s";
    private MqttClient client;
    private MqttConnectOptions options;
    private String userName = "admin";
    private String passWord = "password";

    public void start() throws MqttException {
        // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        // MQTT的连接设置
        options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        // 设置连接的用户名
        options.setUserName(userName);
        // 设置连接的密码
        options.setPassword(passWord.toCharArray());

        client.connect(options);
        //订阅消息
        int[] Qos  = {1};
        String[] topic1 = {TOPIC1};
        client.subscribe(topic1, Qos);

        // 设置回调
        client.setCallback(new MqttCallback(){
            public void connectionLost(Throwable cause) {
                System.out.println("connectionLost, retry...");
            }

            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("deliveryComplete---------" + token.isComplete());
            }
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                try {
                    str = message.toString();
                    System.out.println(" 从服务器收到的消息为:"+message.toString());
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
    public void subscribe(String clientId, String[] topics) throws MqttException {

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
                    String str = message.toString();
                    DeviceStateRep rep = AnalyticUtil.analytic(str);
                    System.out.println(" 从服务器收到的消息为:" + str.toString());
                    System.out.println(" 转换为:" + rep.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}