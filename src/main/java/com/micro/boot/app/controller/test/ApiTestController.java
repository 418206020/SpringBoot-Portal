package com.micro.boot.app.controller.test;

import com.google.gson.Gson;
import com.micro.boot.app.object.request.UserLoginBean;
import com.micro.boot.app.object.response.UserLoginOut;
import com.micro.boot.common.Constants;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.response.ReturnAppInfo;
import com.micro.boot.common.response.ReturnMapInfo;
import com.micro.boot.common.utils.PropertiesConfig;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.app.annotation.AuthIgnore;
import com.micro.boot.app.annotation.Login;
import com.micro.boot.app.annotation.LoginUser;
import com.micro.boot.common.utils.Tools;
import com.micro.boot.modules.user.entity.UserEntity;
import com.micro.boot.thirdparty.ucpaas.send.PostApp;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;

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
     * 生成加密请求数据
     */
    @AuthIgnore
    @GetMapping("data/bodyinfo")
    public BodyInfo bodyinfo() {

        Object data = null;

        data=getLoginData();//登录信息
        return BodyInfo.build(Constants.VERSION_APP,data);
    }

    /**
     * 生成加密返回数据
     */
    @AuthIgnore
    @GetMapping("data/returninfo")
    public ReturnAppInfo returninfo() {

        Object data = null;

        data=getLoginData();//登录信息
        return ReturnAppInfo.successEncrypt(data);
    }



    private static UserLoginBean getLoginData(){
        UserLoginBean userLoginBean = new UserLoginBean();
        userLoginBean.setMobile("15094011640");
        userLoginBean.setPassword(DigestUtils.sha256Hex("568653"));//加密密码传输
        return userLoginBean;
    }

    /**
     * 忽略Token验证测试
     */
    @AuthIgnore
    @GetMapping("notToken")
    public ReturnMapInfo notToken() {
        redisUtils.set("key_test",
                PropertiesConfig.getInstance().getProperty(Constants.UCPAAS_CONFIG + Constants.SEPPARATOR_DOT + "sid"));
        return ReturnMapInfo.ok().put("msg", "无需token也能访问。。。");
    }

    /**
     * 发送短信验证码
     */
    @AuthIgnore
    @GetMapping("sendSms")
    public ReturnMapInfo sendSms() {
        String userName = "huliang";
        String mobile = "15094011640";
        String verifyCode = String.valueOf(Tools.getRandomNum());
        PostApp.sendSms(userName, verifyCode, mobile);
        return ReturnMapInfo.ok().put("msg", "验证码=" + verifyCode);
    }


    /**
     * 示例
     * @param bodyInfo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "用户登录", notes = "用户登录后返回token和用户信息", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 401, message = "token失效"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @PostMapping("user")
    public ReturnAppInfo<UserLoginOut> loginMap(@RequestBody BodyInfo bodyInfo) throws Exception {
        //解密
        UserLoginBean userLoginBean = new Gson().fromJson(bodyInfo.decryptData(), UserLoginBean.class);

        UserLoginOut userLoginOut = new UserLoginOut();
        userLoginOut.setMobile("153355");
        return ReturnAppInfo.success().setEncryptData(userLoginOut);//输出不加密
    }

}
