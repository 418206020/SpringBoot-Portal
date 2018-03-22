package com.micro.boot.app.controller.test;


import com.google.gson.Gson;
import com.micro.boot.common.Constants;
import com.micro.boot.common.utils.AppBaseResult;
import com.micro.boot.common.validator.Assert;
import com.micro.boot.app.service.test.AppUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 注册
 * @author huliang
 * @email 418206020@qq.com
 * @date 2018-01-26 17:27
 */
@RestController
@RequestMapping(Constants.APP)
public class ApiRegisterController {

    @Resource(name = "appUserService")
    private AppUserService appUserService;

    /**
     * 注册
     */
    @PostMapping("register")
    public AppBaseResult register(@RequestBody AppBaseResult appBaseResult) throws Exception {
        HashMap<String,Object> pd = new Gson().fromJson(appBaseResult.decryptData(),HashMap.class);
        Assert.isNull(pd.get("mobile"), "手机号不能为空");
        Assert.isNull(pd.get("password"), "密码不能为空");
        appUserService.save(pd);
        return AppBaseResult.success();
    }
}
