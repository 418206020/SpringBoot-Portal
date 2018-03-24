package com.micro.boot.app.controller.test;

import com.micro.boot.common.Constants;
import com.micro.boot.common.utils.PropertiesConfig;
import com.micro.boot.common.utils.RequestInfo;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.app.annotation.AuthIgnore;
import com.micro.boot.app.annotation.Login;
import com.micro.boot.app.annotation.LoginUser;
import com.micro.boot.common.utils.Tools;
import com.micro.boot.modules.user.entity.UserEntity;
import com.micro.boot.thirdparty.ucpaas.send.PostApp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * APP测试接口
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2018-01-23 15:47
 */
@RestController
@RequestMapping(Constants.APP)
public class ApiTestController {

    @Resource
    private RedisUtils redisUtils;

    /**
     * 获取用户信息
     */
    @Login
    @GetMapping("userInfo")
    public RequestInfo userInfo(@LoginUser UserEntity user) {
        return RequestInfo.ok().put("user", user);
    }

    /**
     * 获取用户ID
     */
    @Login
    @GetMapping("userId")
    public RequestInfo userInfo(@RequestAttribute("userId") Integer userId) {
        return RequestInfo.ok().put("userId", userId);
    }

    /**
     * 忽略Token验证测试
     */
    @AuthIgnore
    @GetMapping("notToken")
    public RequestInfo notToken() {
        redisUtils.set("key_test",
                PropertiesConfig.getInstance().getProperty(Constants.UCPAAS_CONFIG + Constants.SEPPARATOR_DOT + "sid"));
        return RequestInfo.ok().put("msg", "无需token也能访问。。。");
    }

    /**
     * 发送短信验证码
     */
    @AuthIgnore
    @GetMapping("sendSms")
    public RequestInfo sendSms() {
        String userName = "huliang";
        String mobile = "15094011640";
        String verifyCode = String.valueOf(Tools.getRandomNum());
        PostApp.sendSms(userName, verifyCode, mobile);
        return RequestInfo.ok().put("msg", "验证码=" + verifyCode);
    }

}
