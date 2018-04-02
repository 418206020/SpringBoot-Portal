package com.micro.boot;

import com.google.gson.Gson;
import com.micro.boot.app.annotation.AuthIgnore;
import com.micro.boot.app.object.request.UserLoginReq;
import com.micro.boot.app.object.request.UserRegisterReq;
import com.micro.boot.common.Constants;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.response.ReturnAppInfo;
import com.micro.boot.common.utils.Tools;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;

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
        boolean isDecrpt = true;//解密；false加密
        if (isDecrpt) {
            decrypData();
        } else {
            String encryptData = String.valueOf(new Test().bodyinfo());//生成加密后的“请求数据”；快速构造测试数据
//          String encryptData = String.valueOf(new Test().returninfo());//生成加密后的“返回数据”
            System.out.println("data:" + encryptData);
        }
        return;
    }

    /**
     * 构造数据:
     * 请实现具体的构造数据方法，比如：getRegisterReq()
     *
     * @return
     */
    private static Object generator() {
//        data = getRegisterReq();//注册信息
//        data=getLoginReq();//登录信息
        return data;
    }


    public Test() {
        return;
    }

    /**
     * 解密
     */
    private static void decrypData() {
        //测试返回的结果
        ReturnAppInfo returnAppInfo = new ReturnAppInfo();
        data = "ef9rwbm3niTg1T1M8gUubb5mirIk0BXkvkSAt7t1zc6rJo6il92IljsnmyQhmVVe/ZbCe3go+1MxEJjRWmLnC3KG7Kwv7QXLE1ZhYxZyD9FA5UpqOMI0q99Mbj1rVEh28Hb4jFYEXgV770K4rVvPW13GLny9QpIAW3PajD9IuTo90UDie2+NDS4LXtunjmmv414X6utLiSobKDW9W2YTBroU2NOw6qW/QReIsPQS4DCzVhn+0Ga/hA==";

        returnAppInfo.setData((String) data);
        UserRegisterReq request = new Gson().fromJson(returnAppInfo.decryptData(), UserRegisterReq.class);
        System.out.print("ok");
    }


    /**
     * 生成加密请求数据
     */
    @AuthIgnore
    @GetMapping("data/bodyinfo")
    public BodyInfo bodyinfo() {
        return BodyInfo.build(Constants.VERSION_APP, generator());
    }

    /**
     * 生成加密返回数据
     */
    @AuthIgnore
    @GetMapping("data/returninfo")
    public ReturnAppInfo returninfo() {
        return ReturnAppInfo.successEncrypt(generator());
    }

    /**
     * 注册信息
     *
     * @return
     */
    private static UserRegisterReq getRegisterReq() {
        UserRegisterReq req = new UserRegisterReq();
        req.setMobile("15094011640");
        req.setVerifyCode("111222");
        req.setCreateTime(new Date());
        //密码加盐参数
        req.setSalt(RandomStringUtils.randomAlphanumeric(Constants.COUNT_SALT));
        req.setNickname("加菲猫");
        req.setUsername("sdjlfd_230_dsdd");
        if (!Tools.isStringFormatCorrect(req.getUsername())) {
            req.setUsername(req.getUsername().replaceAll("-", "_"));
        }
        return req;
    }

    /**
     * 登录信息
     *
     * @return
     */
    private static UserLoginReq getLoginReq() {
        UserLoginReq req = new UserLoginReq();
        req.setMobile("15094011640");
        req.setPassword(DigestUtils.sha256Hex("568653"));//加密密码传输
        return req;
    }


}