package com.micro.boot.app.controller.test;


import com.google.gson.Gson;
import com.micro.boot.app.object.request.UserLoginBean;
import com.micro.boot.common.Constants;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.response.ReturnAppInfo;
import com.micro.boot.common.validator.Assert;
import com.micro.boot.app.service.test.AppUserService;
import com.micro.boot.app.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * APP登录授权
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2018-01-23 15:31
 */
@RestController
@RequestMapping(Constants.APP)
public class ApiLoginController {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "appUserService")
    private AppUserService appUserService;
    @Autowired
    private JwtUtils jwtUtils;

    @ApiOperation(value = "用户登录", notes = "用户登录后返回token和用户信息", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 401, message = "token失效"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @PostMapping("loginMap")
    public ReturnAppInfo loginMap(@RequestBody BodyInfo bodyInfo) throws Exception {
        logger.info("用户登录", bodyInfo.decryptData());
        HashMap<String, Object> pd = new Gson().fromJson(bodyInfo.decryptData(), HashMap.class);
        Assert.isNull(pd.get("mobile"), "手机号不能为空");
        Assert.isNull(pd.get("password"), "密码不能为空");
        if (!Assert.checkCellphone(pd.get("mobile").toString())) {
            throw new RRException("请输入正确的手机号");
        }

        //test
//        pd = new HashMap<String,Object>();
//        pd.put("mobile", "15094011640");
//        pd.put("password", "admin");
        //用户登录
        HashMap<String, Object> user = appUserService.queryByMobile(pd);
        //生成token todo 添加redis
        String token = jwtUtils.generateToken(user.get("user_id"));
        user.put("token", token);
        user.put("expire", jwtUtils.getExpire());
        //
        user.put("password", "admin");
        return ReturnAppInfo.success().setJsonData(user);//输出不加密
    }

    /**
     * Bean实体加密方式
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
    @PostMapping("loginBean")
    public ReturnAppInfo loginBean(@RequestBody BodyInfo bodyInfo) throws Exception {
        logger.info("用户登录", bodyInfo.decryptData());//TODO 暴露给APP端实现数据加密传输
        UserLoginBean pd = new Gson().fromJson(bodyInfo.decryptData(), UserLoginBean.class);
        Assert.isNull(pd.getMobile(), "手机号不能为空");
        Assert.isNull(pd.getPassword(), "密码不能为空");
        if (!Assert.checkCellphone(pd.getMobile())) {
            throw new RRException("请输入正确的手机号");
        }

        //test
//        pd = new HashMap<String,Object>();
//        pd.put("mobile", "15094011640");
//        pd.put("password", "admin");
        //用户登录
        UserLoginBean user = appUserService.queryByMobileBean(pd);
        //生成token todo 添加redis
        String token = jwtUtils.generateToken(user.getUserId());
        user.setToken(token);
        user.setExpire(String.valueOf(jwtUtils.getExpire()));
        // 78
        user.setPassword("admin");
//        return ReturnAppInfo.success().setEncryptData(ReturnMapInfo.ok().put("msg",user));//todo 加密数据封装成Json？
        return ReturnAppInfo.success().setEncryptData(user);
    }
}
