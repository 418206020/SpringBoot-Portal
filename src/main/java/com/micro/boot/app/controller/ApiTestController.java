package com.micro.boot.app.controller;
import com.micro.boot.common.utils.R;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.app.annotation.AuthIgnore;
import com.micro.boot.app.annotation.Login;
import com.micro.boot.app.annotation.LoginUser;
import com.micro.boot.modules.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/app")
public class ApiTestController {

    @Resource
    private RedisUtils redisUtils;

    /**
     * 获取用户信息
     */
    @Login
    @GetMapping("userInfo")
    public R userInfo(@LoginUser UserEntity user){
        return R.ok().put("user", user);
    }

    /**
     * 获取用户ID
     */
    @Login
    @GetMapping("userId")
    public R userInfo(@RequestAttribute("userId") Integer userId){
        return R.ok().put("userId", userId);
    }

    /**
     * 忽略Token验证测试
     */
    @AuthIgnore
    @GetMapping("notToken")
    public R notToken(){
        redisUtils.set("key_test","keyValue1s");
        return R.ok().put("msg", "无需token也能访问。。。");
    }

}
