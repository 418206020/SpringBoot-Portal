package com.micro.boot.app.controller.user;


import com.google.gson.Gson;
import com.micro.boot.app.object.request.UserLoginBean;
import com.micro.boot.app.object.response.UserLoginOut;
import com.micro.boot.app.service.test.AppUserService;
import com.micro.boot.app.utils.JwtUtils;
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
@RequestMapping(Constants.APP + Constants.SEPPARATOR_SLASH + ModuleConstant.MODULE_LOGIN)
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "appUserService")
    private AppUserService appUserService;
    @Autowired
    private JwtUtils jwtUtils;


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
    @PostMapping("user")
    public ReturnAppInfo<UserLoginOut> loginMap(@RequestBody BodyInfo bodyInfo, @RequestHeader HttpHeaders headers) throws Exception {
        logger.info("用户登录", bodyInfo.decryptData());
        //解密
        UserLoginBean userLoginBean = new Gson().fromJson(bodyInfo.decryptData(), UserLoginBean.class);

        UserLoginOut userLoginOut = new UserLoginOut();
        userLoginOut.setMobile("153355");
        return ReturnAppInfo.successEncrypt(userLoginOut);
//        return ReturnAppInfo.success().setEncryptData(userLoginOut);//
    }

}