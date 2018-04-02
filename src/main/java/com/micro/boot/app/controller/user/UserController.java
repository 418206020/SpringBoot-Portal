package com.micro.boot.app.controller.user;


import com.google.gson.Gson;
import com.micro.boot.app.object.request.PasswordRestReq;
import com.micro.boot.app.object.request.UserLoginReq;
import com.micro.boot.app.object.request.UserRegisterReq;
import com.micro.boot.app.object.response.UserLoginRep;
import com.micro.boot.app.object.response.UserRegisterRep;
import com.micro.boot.app.service.user.UserService;
import com.micro.boot.app.utils.JwtUtils;
import com.micro.boot.common.AppRestUrl;
import com.micro.boot.common.Constants;
import com.micro.boot.common.ModuleConstant;
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
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JwtUtils jwtUtils;

    @Resource UserService userService;


    /**
     * 修改密码
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
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 603, message = "验证码错误或失效"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @PutMapping(AppRestUrl.PASSWORD_RESET)
    public ReturnAppInfo<UserLoginRep> passwordReset(@RequestBody BodyInfo bodyInfo,
                                                @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.REGISTER_MOBILE+",Param:", bodyInfo.toString());

        PasswordRestReq request = new Gson().fromJson(bodyInfo.decryptData(), PasswordRestReq.class);

        userService.passwordReset(request);
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
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @PutMapping(AppRestUrl.PASSWORD_RESET_DEFAULT)
    public ReturnAppInfo<UserLoginRep> passwordResetDefault(@RequestBody BodyInfo bodyInfo,
                                                     @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.REGISTER_MOBILE+",Param:", bodyInfo.toString());

        PasswordRestReq request = new Gson().fromJson(bodyInfo.decryptData(), PasswordRestReq.class);

        userService.passwordReset(request.getMobile());
        //修改密码成功根据code
        return ReturnAppInfo.successEncrypt(null);
    }

    /**
     * 用户登录
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
    @PostMapping("login")
    public ReturnAppInfo<UserLoginRep> userLogin(@RequestBody BodyInfo bodyInfo, @RequestHeader HttpHeaders headers) throws Exception {
        logger.info("用户登录", bodyInfo.decryptData());
        //解密
        UserLoginReq userLoginReq = new Gson().fromJson(bodyInfo.decryptData(), UserLoginReq.class);

        UserLoginRep userLoginRep = new UserLoginRep();
        userLoginRep.setMobile("153355");
        return ReturnAppInfo.successEncrypt(userLoginRep);
    }

}