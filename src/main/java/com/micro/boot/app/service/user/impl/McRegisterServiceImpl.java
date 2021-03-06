package com.micro.boot.app.service.user.impl;

import com.micro.boot.app.dao.McUserDao;
import com.micro.boot.app.object.request.user.McUserRegisterReq;
import com.micro.boot.app.object.response.user.McUserRegisterRep;
import com.micro.boot.app.service.user.McRegisterService;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Constants;
import com.micro.boot.common.Message;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.utils.PwdTools;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.common.utils.Tools;
import com.micro.boot.thirdparty.ucpaas.send.PostApp;
import org.apache.commons.codec.digest.DigestUtils;
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
public class McRegisterServiceImpl implements McRegisterService {

    private static final String DEFALUT_CICKNAME = "用户";
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

        PostApp.sendSms(null, verifyCode, mobile); //TODO 正式开关
//        verifyCode = "111222";// todo 测试
        //存储到redis，时常60秒
        redisUtils
                .set(RedisUtils.redisGetKey(mobile, AppCode.REDIS_VERIFY_CODE), verifyCode, RedisUtils.EXPIRE_SECONDS_60);

        return verifyCode;
    }

    /**
     * 注册用户
     *
     * @param request
     *
     * @return
     */
    @Override public McUserRegisterRep registerUser(McUserRegisterReq request) {
        //校验参数
        if (StringUtils.isEmpty(request.getMobile()) ||
                StringUtils.isEmpty(request.getVerifyCode()))
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
        //校验用户是否存在
        if (Constants.ZERO != mcUserDao.existUserCount(request.getMobile())) {
            throw new RRException(AppCode.CODE_USER_EXIST, Message.MSG_EN_EXIST_USER);
        }
        //设置用户名
        if (StringUtils.isEmpty(request.getUsername())) {
            String rUserName = null;
            do {
                //循环获取不重复用户名
                rUserName = RandomStringUtils.random(Constants.BIT_RANDOM_STRING, true, true);
            } while (isDupUsername(rUserName));
            request.setUsername(rUserName);
        } else {
            //特殊字符
            if (!Tools.isSpecialChar(request.getUsername()) ||
                    isDupUsername(request.getUsername()))
            {
                throw new RRException(AppCode.EXCETPTION_FAIL, Message.MSG_EN_PARAMETERS_ERROR);
            }
        }
        //设置默认昵称
        if (StringUtils.isEmpty(request.getNickname())) {
            request.setNickname(DEFALUT_CICKNAME);
        }
        //设置加盐
        request.setSalt(RandomStringUtils.randomAlphanumeric(Constants.COUNT_SALT));
        String pwd = request.getPassword();
        if (StringUtils.isEmpty(request.getPassword())) {
            //设置随机密码-首次密码复杂度不做要求
            pwd = Tools.getRandomBit8();
        }
        request.setPassword(
                PwdTools.encodeHexPwd(pwd,request.getSalt())
        );
        request.setStatus(Constants.STATUS_NORMAL);
        //注册新用户
        if (Constants.ZERO == mcUserDao.registerMcUser(request)) {
            throw new RRException(AppCode.EXCETPTION_DATABASE_FAIL, Message.MSG_EN_DATABASE);
        }
        McUserRegisterRep response = mcUserDao.getUserByMobile(request.getMobile());
        response.setPassword(pwd);
        return response;
    }

    /**
     * 校验用户名重复
     *
     * @param username
     *
     * @return true 重复
     */
    @Override public Boolean isDupUsername(String username) {
        if (mcUserDao.isDupUsername(username) == Constants.ZERO) {
            return false;
        }
        return true;
    }


}