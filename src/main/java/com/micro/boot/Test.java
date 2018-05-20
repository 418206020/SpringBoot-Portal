package com.micro.boot;

import com.google.gson.Gson;
import com.micro.boot.app.object.McRequestPage;
import com.micro.boot.app.object.request.user.McPasswordResetReq;
import com.micro.boot.app.object.request.user.McUserLoginReq;
import com.micro.boot.app.object.request.user.McUserRegisterReq;
import com.micro.boot.app.utils.AppUtils;
import com.micro.boot.common.Constants;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.response.ReturnAppInfo;
import com.micro.boot.common.utils.PwdTools;
import com.micro.boot.thirdparty.paho.ClientSearch;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Date;

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
        final long timeInterval = 20000;// 两秒运行一次
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    // ------- code for task to run
                    ClientSearch clientSearch = new ClientSearch();
                    try {
                        String[] topics = {Constants.M2M + "1234567890000000"};
                        clientSearch.subscribe("13488120792", topics);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }

                    // ------- ends here
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