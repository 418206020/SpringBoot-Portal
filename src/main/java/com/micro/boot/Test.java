package com.micro.boot;

import com.micro.boot.common.Constants;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.thirdparty.paho.MQTTClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.UUID;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2018/4/2
 * @since 1.0.0
 */
public class Test {

    private static Object data;

    public static void main(String[] args) {

        runTask();

//        McRequestPage page = new McRequestPage();
//        page.setOrderBy("createTime");
//        page.setOrderDesc("asc");
////        ;
//        System.out.println("00"+AppUtils.getOrderString(page));


//        McUserLoginReq req = new McUserLoginReq();
//        req.setMobile("15094011640");
//        req.setPassword("DK_OWK39DK");//密码传输
//        //--------------------构造测试数据------------------------
//        String bodyContent = JSONObject.fromObject(getData(req).toString()).toString();
//        System.out.println("TEST-REQUEST-DATA:" + getData(req).toString());
//
//



        return;
    }


    public static void runTask() {
        final long timeInterval = 30*000;// 30秒运行一次
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    // ------- code for task to run
                    try {       //你要运行的程序
                        MQTTClient MQTTClient = new MQTTClient();
                        try {
                            String[] topics = {Constants.M2M + "#"};
                            String clientID = UUID.randomUUID().toString();
                            MQTTClient.subscribe(clientID, topics);
//                            MQTTClient.start();
//                            MQTTClient.disconnect();
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

    /**
     * 生成加密请求数据
     */
    public static BodyInfo getData(Object object) {
        return BodyInfo.build(Constants.VERSION_APP, object);
    }

}