package com.micro.boot.app.controller.user;


import com.google.gson.Gson;
import com.micro.boot.app.object.request.McUserRegisterReq;
import com.micro.boot.app.object.response.McUserLoginRep;
import com.micro.boot.app.object.response.McUserRegisterRep;
import com.micro.boot.app.service.user.McRegisterService;
import com.micro.boot.common.*;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.response.ReturnAppInfo;
import com.micro.boot.common.utils.RedisUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class McRegisterController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private McRegisterService mcRegisterService;

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
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @GetMapping(AppRestUrl.SMS_VERRIFY_CODE)
    public ReturnAppInfo<McUserLoginRep> loginMap(@PathVariable String mobile,
                                                  @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.SMS_VERRIFY_CODE+",Param:", mobile);
        //发送短信并返回验证码
        String verifyCode = mcRegisterService.sendSmsVerifyCode(mobile);
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
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.CODE_ERROR_VERIFY_CODE, message = Message.MSG_EN_ERROR_VERIFY_CODE + " or " + Message.MSG_EN_ERROR_PASSWORD),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @PostMapping(AppRestUrl.REGISTER_MOBILE)
    public ReturnAppInfo<McUserLoginRep> loginMap(@RequestBody BodyInfo bodyInfo,
                                                  @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.REGISTER_MOBILE+",Param:", bodyInfo.toString());

        McUserRegisterReq request = new Gson().fromJson(bodyInfo.decryptData(), McUserRegisterReq.class);

        McUserRegisterRep response = mcRegisterService.registerUser(request);
        return ReturnAppInfo.successEncrypt(response);
    }


}