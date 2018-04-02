package com.micro.boot;

import com.google.gson.Gson;
import com.micro.boot.app.object.request.UserRegisterReq;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.response.ReturnAppInfo;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2018/4/2
 * @since 1.0.0
 */
public class Test {


    public static void main(String[] args){
        //测试返回的结果
        ReturnAppInfo returnAppInfo = new ReturnAppInfo();
        String data = "ef9rwbm3niTg1T1M8gUubb5mirIk0BXkvkSAt7t1zc6rJo6il92IljsnmyQhmVVe/ZbCe3go+1MxEJjRWmLnC3KG7Kwv7QXLE1ZhYxZyD9FA5UpqOMI0q99Mbj1rVEh28Hb4jFYEXgV770K4rVvPW13GLny9QpIAW3PajD9IuTo90UDie2+NDS4LXtunjmmv414X6utLiSobKDW9W2YTBroU2NOw6qW/QReIsPQS4DCzVhn+0Ga/hA==";
        returnAppInfo.setData(data);
        UserRegisterReq request = new Gson().fromJson(returnAppInfo.decryptData(), UserRegisterReq.class);
        return;
    }
}