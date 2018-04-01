package com.micro.boot.app.controller.user;


import com.google.gson.Gson;
import com.micro.boot.app.object.request.UserLoginReq;
import com.micro.boot.app.object.request.UserRegisterReq;
import com.micro.boot.app.object.response.UserLoginRep;
import com.micro.boot.app.object.response.UserRegisterRep;
import com.micro.boot.app.service.test.AppUserService;
import com.micro.boot.app.service.user.RegisterService;
import com.micro.boot.app.utils.JwtUtils;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.AppRestUrl;
import com.micro.boot.common.Constants;
import com.micro.boot.common.ModuleConstant;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.response.ReturnAppInfo;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.common.utils.Tools;
import com.micro.boot.modules.sys.service.ShiroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * APP登录授权
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2018-01-23 15:31
 */
@RestController
@RequestMapping(Constants.APP + Constants.SEPPARATOR_SLASH + ModuleConstant.MODULE_REGISTER)
public class RegisterController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RegisterService registerService;

    @Resource
    private RedisUtils redisUtils;


    /**
     * 获取短信验证码
     *
     * @param mobile
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "获取短信验证码", notes = "使用手机号获取短信验证码", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 401, message = "token失效"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @GetMapping(AppRestUrl.SMS_VERRIFY_CODE)
    public ReturnAppInfo<UserLoginRep> loginMap(@PathVariable String mobile,
                                                @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.SMS_VERRIFY_CODE+",Param:", mobile);
        //发送短信并返回验证码
        String verifyCode =registerService.sendSmsVerifyCode(mobile);
        return ReturnAppInfo.successEncrypt(verifyCode);
    }

    /**
     * 使用短信验证码注册
     *
     * @param bodyInfo
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "使用短信验证码注册", notes = "使用短信验证码注册", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 401, message = "token失效"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @PutMapping(AppRestUrl.REGISTER_MOBILE)
    public ReturnAppInfo<UserLoginRep> loginMap(@RequestBody BodyInfo bodyInfo,
                                                @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.REGISTER_MOBILE+",Param:", bodyInfo.toString());

        UserRegisterReq request = new Gson().fromJson(bodyInfo.decryptData(), UserRegisterReq.class);

        UserRegisterRep response = registerService.registerUser(request);
        return ReturnAppInfo.successEncrypt(response);
    }


}