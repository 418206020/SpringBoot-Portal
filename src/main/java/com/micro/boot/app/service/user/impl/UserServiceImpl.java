package com.micro.boot.app.service.user.impl;

import com.micro.boot.app.dao.McUserDao;
import com.micro.boot.app.object.request.PasswordRestReq;
import com.micro.boot.app.object.request.UserRegisterReq;
import com.micro.boot.app.object.response.UserRegisterRep;
import com.micro.boot.app.service.user.RegisterService;
import com.micro.boot.app.service.user.UserService;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Constants;
import com.micro.boot.common.Message;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.common.utils.Tools;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 〈注册〉
 *
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private RedisUtils redisUtils;

    @Resource McUserDao mcUserDao;

    private static final String DEFAULT_PWD = "11111111";


    /**
     * 通过手机 重置用户密码
     * @param mobile
     */
    @Override public void passwordReset(String mobile) {
        UserRegisterRep userRegister = mcUserDao.getUserByMobile(mobile);
        mcUserDao.updatePasswordByMobile(mobile,
                new Sha256Hash(DEFAULT_PWD, userRegister.getSalt()).toHex());
    }

    /**
     * 修改密码 通过手机
     *
     * @param request
     */
    @Override public void passwordReset(PasswordRestReq request) {
        //校验参数 密码至少6位且由英文字符数字下划线组成
        if (StringUtils.isEmpty(request.getMobile()) ||
                StringUtils.isEmpty(request.getVerifyCode()) ||
                StringUtils.isEmpty(request.getPassword()) ||
                Tools.isStringFormatCorrect(request.getPassword()))
        {
            throw new RRException(AppCode.EXCETPTION_FAIL, Message.MSG_EN_PARAMETERS_ERROR);
        }
        String redisVerifyCode = redisUtils.get(RedisUtils.redisGetKey(request.getMobile(), AppCode.REDIS_VERIFY_CODE));
        //校验验证码是否失效
        if (StringUtils.isEmpty(redisVerifyCode) ||
                !redisVerifyCode.equals(request.getVerifyCode()))
        {
            throw new RRException(AppCode.CODE_ERROR_VERIFY_CODE, Message.MSG_EN_ERROR_VERIFY_CODE);
        }
        UserRegisterRep userRegister = mcUserDao.getUserByMobile(request.getMobile());

        mcUserDao.updatePasswordByMobile(request.getMobile(),
                new Sha256Hash(request.getPassword(), userRegister.getSalt()).toHex());

    }


}