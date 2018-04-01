package com.micro.boot.app.controller.test;

import com.google.gson.Gson;
import com.micro.boot.app.object.request.UserLoginReq;
import com.micro.boot.app.object.request.UserRegisterReq;
import com.micro.boot.app.object.response.UserLoginRep;
import com.micro.boot.common.Constants;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.response.ReturnAppInfo;
import com.micro.boot.common.response.ReturnMapInfo;
import com.micro.boot.common.utils.PropertiesConfig;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.app.annotation.AuthIgnore;
import com.micro.boot.common.utils.Tools;
import com.micro.boot.thirdparty.ucpaas.send.PostApp;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

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


    private Object generator(){
        Object data = null;

//        //密码错误
//        String userpassword = DigestUtils.sha256Hex(password);
//        if (!user.get("password").equals(userpassword)) {
//            throw new RRException("密码错误");
//        }
        data=getRegisterReq();//注册信息
//        data=getLoginReq();//登录信息
        return data;
    }

    /**
     * 生成加密请求数据
     */
    @AuthIgnore
    @GetMapping("data/bodyinfo")
    public BodyInfo bodyinfo() {
        return BodyInfo.build(Constants.VERSION_APP,generator());
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
     * @return
     */
    private static UserRegisterReq getRegisterReq(){
        UserRegisterReq req = new UserRegisterReq();
        req.setMobile("15094011640");
        req.setVerifyCode("111222");
        req.setCreateTime(new Date());
        //密码加盐参数
        req.setSalt(RandomStringUtils.randomAlphanumeric(Constants.COUNT_SALT));
        req.setNickname("加菲猫");
        req.setUsername("sdjlfd_230_dsdd");
        if(!Tools.isStringFormatCorrect(req.getUsername())){
            req.setUsername(req.getUsername().replaceAll("-","_"));
        }
        return req;
    }

    /**
     * 登录信息
     * @return
     */
    private static UserLoginReq getLoginReq(){
        UserLoginReq req = new UserLoginReq();
        req.setMobile("15094011640");
        req.setPassword(DigestUtils.sha256Hex("568653"));//加密密码传输
        return req;
    }









}
