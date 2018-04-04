package com.micro.boot.app.service.user.impl;

import com.micro.boot.app.dao.McUserDao;
import com.micro.boot.app.object.request.McPasswordRestReq;
import com.micro.boot.app.object.request.McUserLoginReq;
import com.micro.boot.app.object.response.McUserLoginRep;
import com.micro.boot.app.object.response.McUserRegisterRep;
import com.micro.boot.app.service.user.McUserService;
import com.micro.boot.app.utils.JwtUtils;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Message;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.utils.DateUtils;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.common.utils.Tools;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 〈注册〉
 *
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
@Service
@Transactional
public class McUserServiceImpl implements McUserService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private JwtUtils jwtUtils;

    @Resource McUserDao mcUserDao;

    private static final String DEFAULT_PWD = "11111111";


    /**
     * 通过手机 重置用户密码
     *
     * @param mobile
     */
    @Override public void passwordReset(String mobile) {
        McUserRegisterRep userRegister = mcUserDao.getUserByMobile(mobile);
        mcUserDao.updatePasswordByMobile(mobile,
                new Sha256Hash(DEFAULT_PWD, userRegister.getSalt()).toHex());
    }

    /**
     * 修改密码 通过手机
     *
     * @param request
     */
    @Override public void passwordReset(McPasswordRestReq request) {
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
        McUserRegisterRep userRegister = mcUserDao.getUserByMobile(request.getMobile());

        mcUserDao.updatePasswordByMobile(request.getMobile(),
                new Sha256Hash(request.getPassword(), userRegister.getSalt()).toHex());

    }

    /**
     * 登录
     *
     * @param request
     *
     * @return
     */
    @Override public McUserLoginRep loginByPasswordOrVerifyCode(McUserLoginReq request) {
        //校验参数 密码至少6位且由英文字符数字下划线组成
        if ((StringUtils.isEmpty(request.getVerifyCode()) && StringUtils.isEmpty(request.getPassword())) ||
                Tools.isStringFormatCorrect(request.getMobile()))
        {
            throw new RRException(AppCode.EXCETPTION_FAIL, Message.MSG_EN_PARAMETERS_ERROR);
        }
        //两种登录方式
        if (!StringUtils.isEmpty(request.getVerifyCode())) {
            //从redis获取验证码
            String redisVerifyCode = redisUtils
                    .get(RedisUtils.redisGetKey(request.getMobile(), AppCode.REDIS_VERIFY_CODE));
            //校验验证码是否失效
            if (!redisVerifyCode.equals(request.getVerifyCode())) {
                throw new RRException(AppCode.CODE_ERROR_VERIFY_CODE, Message.MSG_EN_ERROR_VERIFY_CODE);
            }
        } else if (!StringUtils.isEmpty(request.getPassword())) {
            //校验密码是否正确
            McUserRegisterRep userRegister = mcUserDao.getUserByMobile(request.getMobile());
            if (!userRegister.getPassword().equals(
                    new Sha256Hash(request.getPassword(), userRegister.getSalt()).toHex()))
            {
                throw new RRException(AppCode.CODE_ERROR_PASSWORD, Message.MSG_EN_ERROR_PASSWORD);
            }
        }
        McUserLoginRep response = mcUserDao.getUserByLogin(request.getMobile());
        //生成token
        response.setToken(jwtUtils.generateToken(response.getMobile()));
        redisUtils.set(RedisUtils.redisSetKey(request.getMobile(), AppCode.REDIS_MOBILE_TOKEN), response.getToken(),
                RedisUtils.DEFAULT_EXPIRE);
        //更新登录token
        // 考虑redis存一天，数据库存7天.
        // 业务验证token取redis后取数据库；如果redis存在，延长redis时长1天（连续7天不登录失效）
        // 如果数据库已过期，同时更新，没过期只重新登录
//        tokenRecord(response.getId(), response.getMobile(), response.getToken(), null);//待重构
        return response;
    }

    /**
     * 更新登录token
     * @param userId
     * @param mobile
     * @param token
     * @param expireTime
     */
    @Override
    public void tokenRecord(long userId, String mobile, String token, Date expireTime) {
        Date updateTime = new Date();
        if(null == expireTime) {
            expireTime = DateUtils.getNextWeek(updateTime);
        }
        mcUserDao.saveLoginToken(userId, mobile, token, expireTime,updateTime);
    }


}