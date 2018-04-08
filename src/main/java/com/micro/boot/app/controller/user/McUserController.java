package com.micro.boot.app.controller.user;


import com.google.gson.Gson;
import com.micro.boot.app.annotation.MobileToken;
import com.micro.boot.app.object.request.McPasswordResetReq;
import com.micro.boot.app.object.request.McUserLoginReq;
import com.micro.boot.app.object.response.McUserInfoRep;
import com.micro.boot.app.object.response.McUserLoginRep;
import com.micro.boot.app.service.user.McUserService;
import com.micro.boot.app.utils.JwtUtils;
import com.micro.boot.common.*;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.response.ReturnAppInfo;
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
@RequestMapping(Constants.APP + Constants.SEPPARATOR_SLASH + ModuleConstant.MODULE_USER)
public class McUserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JwtUtils jwtUtils;

    @Resource McUserService mcUserService;


    /**
     * 修改密码:登陆后可以修改，通过MobileToken拦截校验
     *
     * @param bodyInfo
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "修改密码", notes = "修改密码", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Message.MSG_OK_200),
            @ApiResponse(code = 606, message = Message.MSG_EN_INPUT_ERROR),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @PutMapping(AppRestUrl.PASSWORD_RESET)
    public ReturnAppInfo<McUserLoginRep> passwordReset(@RequestBody BodyInfo bodyInfo,
                                                       @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.PASSWORD_RESET + ",Param:", bodyInfo.toString());

        McPasswordResetReq request = new Gson().fromJson(bodyInfo.decryptData(), McPasswordResetReq.class);
        //将head中传参到request
        mcUserService.passwordReset(request, headers.get("token").get(0));
        //修改密码成功根据code
        return ReturnAppInfo.successEncrypt(null);
    }

    /**
     * 重置密码
     *
     * @param bodyInfo
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "重置密码", notes = "重置密码", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @PutMapping(AppRestUrl.PASSWORD_RESET_DEFAULT)
    public ReturnAppInfo<McUserLoginRep> passwordResetDefault(@RequestBody BodyInfo bodyInfo,
                                                              @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.PASSWORD_RESET_DEFAULT + ",Param:", bodyInfo.toString());

        McPasswordResetReq request = new Gson().fromJson(bodyInfo.decryptData(), McPasswordResetReq.class);

        mcUserService.passwordReset(request.getMobile());
        //修改密码成功
        return ReturnAppInfo.successEncrypt(null);
    }

    /**
     * 用户登录 通过短信验证码或者密码均可
     *
     * @param bodyInfo
     *
     * @return 返回带token的用户基本信息 McUserLoginRep
     *
     * @throws Exception
     */
    @ApiOperation(value = "用户登录", notes = "用户登录后返回token和用户信息", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.CODE_ERROR_VERIFY_CODE, message = Message.MSG_EN_ERROR_VERIFY_CODE),
            @ApiResponse(code = AppCode.CODE_ERROR_PASSWORD, message = Message.MSG_EN_ERROR_PASSWORD),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @PostMapping(AppRestUrl.LOGIN_PWD_VERIFYCODE)
    public ReturnAppInfo<McUserLoginRep> userLogin(@RequestBody BodyInfo bodyInfo,
                                                   @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.LOGIN_PWD_VERIFYCODE + ",Param:", bodyInfo.toString());
        //解析登录请求数据
        McUserLoginReq mcUserLoginReq = new Gson().fromJson(bodyInfo.decryptData(), McUserLoginReq.class);

        McUserLoginRep mcUserLoginRep = mcUserService.loginByPasswordOrVerifyCode(mcUserLoginReq);
        return ReturnAppInfo.successEncrypt(mcUserLoginRep);
    }

    /**
     * 退出登录 通过token
     *
     * @param headers
     *
     * @return 返回
     *
     * @throws Exception
     */
    @ApiOperation(value = "退出登录", notes = "退出登录", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Message.MSG_OK_200),
            @ApiResponse(code = 401, message = Message.MSG_EN_ERROR_VERIFY_CODE),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @MobileToken
    @PutMapping(AppRestUrl.LOGOUT)
    public ReturnAppInfo<McUserLoginRep> userLogout(@RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.LOGOUT + ",Param:");

        String mobile = headers.get("mobile").get(0);
        mcUserService.logout(mobile);
        return ReturnAppInfo.successEncrypt("ok");
    }

    /**
     * 查询个人基本信息
     *
     * @param headers
     *
     * @return 返回带token的用户基本信息 McUserLoginRep
     *
     * @throws Exception
     */
    @ApiOperation(value = "退出登录", notes = "退出登录", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Message.MSG_OK_200),
            @ApiResponse(code = 401, message = Message.MSG_EN_ERROR_VERIFY_CODE),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @MobileToken
    @GetMapping(AppRestUrl.MC_INFO)
    public ReturnAppInfo<McUserLoginRep> userLogin(@RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.MC_INFO + ",Param:");

        String mobile = headers.get("mobile").get(0);
        McUserInfoRep mcUserInfoRep = mcUserService.getUserInfo(mobile);
        return ReturnAppInfo.successEncrypt(mcUserInfoRep);
    }
}