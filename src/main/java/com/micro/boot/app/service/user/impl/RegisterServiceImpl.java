package com.micro.boot.app.service.user.impl;

import com.micro.boot.app.dao.McUserDao;
import com.micro.boot.app.object.request.UserRegisterReq;
import com.micro.boot.app.object.response.UserRegisterRep;
import com.micro.boot.app.service.user.RegisterService;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Message;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.response.ReturnAppInfo;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.common.utils.Tools;
import com.micro.boot.thirdparty.ucpaas.send.PostApp;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 〈注册〉
 *
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private RedisUtils redisUtils;

    @Resource McUserDao mcUserDao;

    /**
     * 发送短信验证码
     *
     * @param mobile
     *
     * @return
     */
    @Override public String sendSmsVerifyCode(String mobile) {

        //校验手机号
        if (!Tools.checkMobileNumber(mobile)) {
            throw new RRException(AppCode.CODE_MOBILE_ERROR, Message.MSG_EN_ERROR_MOBILE);
        }
        //生成验证码
        String verifyCode = String.valueOf(Tools.getRandomNum());

//        PostApp.sendSms(null, verifyCode, mobile); //TODO 正式开关
        verifyCode = "111222";// todo 测试

        //存储到redis，时常60秒
        redisUtils.set(mobile, verifyCode, RedisUtils.EXPIRE_TEST);//

        return verifyCode;
    }

    /**
     * 注册用户
     *
     * @param request
     *
     * @return
     */
    @Override public UserRegisterRep registerUser(UserRegisterReq request) {
        //校验参数
        if (StringUtils.isEmpty(request.getMobile()) ||
                StringUtils.isEmpty(request.getVerifyCode()) ||
                StringUtils.isEmpty(request.getUsername()))
        {
            throw new RRException(AppCode.EXCETPTION_FAIL, Message.MSG_EN_PARAMETERS_ERROR);
        }
        //校验token是否失效
        if (StringUtils.isEmpty(redisUtils.get(request.getMobile())) ||
                !redisUtils.get(request.getMobile()).equals(request.getVerifyCode()))
        {
            throw new RRException(AppCode.CODE_TOKEN_FAIL, Message.MSG_EN_ERROR_TOKEN);
        }

        //校验用户是否存在
        if(mcUserDao.existUserByMobile(request.getMobile()))
        {
            throw new RRException(AppCode.EXCETPTION_FAIL, Message.MSG_EN_EXIST_USER);
        }
        //注册新用户
        UserRegisterRep response = mcUserDao.registerMcUser(request);;
        return response;
    }


}