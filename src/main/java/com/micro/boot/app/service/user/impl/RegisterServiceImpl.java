package com.micro.boot.app.service.user.impl;

import com.micro.boot.app.service.user.RegisterService;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.common.utils.Tools;
import com.micro.boot.thirdparty.ucpaas.send.PostApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈注册〉
 *
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 发送短信验证码
     *
     * @param mobile
     *
     * @return
     */
    @Override public String sendSmsVerifyCode(String mobile) {

        //生成验证码
        String verifyCode = String.valueOf(Tools.getRandomNum());

//        PostApp.sendSms(null, verifyCode, mobile); //TODO 正式开关
        verifyCode = "111222";// todo 测试

        //存储到redis，时常60秒
        redisUtils.set(mobile,verifyCode,RedisUtils.EXPIRE_TEST);//

        return verifyCode;
    }


}