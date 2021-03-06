package com.micro.boot.app.service.user.impl;

import com.micro.boot.app.dao.McUserDao;
import com.micro.boot.app.object.request.user.McPasswordResetReq;
import com.micro.boot.app.object.request.user.McUserInfoReq;
import com.micro.boot.app.object.request.user.McUserLoginReq;
import com.micro.boot.app.object.response.user.McUserInfoRep;
import com.micro.boot.app.object.response.user.McUserLoginRep;
import com.micro.boot.app.object.response.user.McUserRegisterRep;
import com.micro.boot.app.service.user.McUserService;
import com.micro.boot.app.utils.JwtUtils;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Constants;
import com.micro.boot.common.Message;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.utils.DateUtils;
import com.micro.boot.common.utils.PwdTools;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.common.utils.Tools;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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


    /**
     * 通过手机 重置用户密码
     *
     * @param request
     */
    @Override public void passwordReset(McPasswordResetReq request) {
        McUserRegisterRep userRegister = mcUserDao.getUserByMobile(request.getMobile());
        //校验参数 密码至少8位且由英文字符数字下划线组成
        if (StringUtils.isEmpty(request.getPassword()) ||
                !PwdTools.isCorrect_1_8(request.getPassword()) ||
                !PwdTools.isCorrect_2(request.getPassword()))
        {
            throw new RRException(AppCode.CODE_ERROR_INPUT, Message.MSG_EN_INPUT_ERROR
                    + " 必须包含数字、字母、特殊字符三种:支持特殊字符范围：^$./,;:'!@#%&*|?-_+(){}[]");
        }
        //sha256，加盐，shiro加密
        mcUserDao.updatePasswordByMobile(request.getMobile(),
                PwdTools.encodeHexPwd(request.getPassword(),userRegister.getSalt())
                );
    }

    /**
     * 修改密码 通过手机
     *
     * @param request
     */
    @Override public void passwordReset(McPasswordResetReq request, List<String> tokenList) {
        String token = null;
        if (null != tokenList) {
            token = tokenList.get(0);
        }
        //校验权限
        if (StringUtils.isEmpty(request.getVerifyCode()) && StringUtils.isEmpty(token)) {
            throw new RRException(AppCode.EXCETPTION_FAIL, Message.MSG_EN_PARAMETERS_ERROR);
        } else if (!StringUtils.isEmpty(request.getVerifyCode()) && StringUtils.isEmpty(token)) {
            //手机号对应验证码是否失效
            String redisVerifyCode = redisUtils
                    .get(RedisUtils.redisGetKey(request.getMobile(), AppCode.REDIS_VERIFY_CODE));
            //校验验证码是否失效
            if (!redisVerifyCode.equals(request.getVerifyCode())) {
                throw new RRException(AppCode.CODE_ERROR_VERIFY_CODE, Message.MSG_EN_ERROR_VERIFY_CODE);
            }
        }
        //校验参数 密码至少8位且由英文字符数字下划线组成
        if (StringUtils.isEmpty(request.getPassword()) ||
                !PwdTools.isCorrect_1_8(request.getPassword()) ||
                !PwdTools.isCorrect_2(request.getPassword()))
        {
            throw new RRException(AppCode.CODE_ERROR_INPUT, Message.MSG_EN_INPUT_ERROR
                    + " 必须包含数字、字母、特殊字符三种:支持特殊字符范围：^$./,;:'!@#%&*|?-_+(){}[]");
        }
        //获取加盐
        McUserRegisterRep userRegister = mcUserDao.getUserByMobile(request.getMobile());
        mcUserDao.updatePasswordByMobile(request.getMobile(),
                PwdTools.encodeHexPwd(request.getPassword(),userRegister.getSalt()));

    }

    /**
     * 登录
     *
     * @param request
     *
     * @return
     */
    @Override public McUserLoginRep loginByPasswordOrVerifyCode(McUserLoginReq request) {
        //校验参数
        if (StringUtils.isEmpty(request.getVerifyCode()) && StringUtils.isEmpty(request.getPassword())) {
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
            if (null == userRegister) {
                throw new RRException(AppCode.CODE_ERROR_USER, Message.MSG_NOT_EXIST_USER);
            }
            if (!userRegister.getPassword().equals(
                    PwdTools.encodeHexPwd(request.getPassword(), userRegister.getSalt())
            ))
            {
                throw new RRException(AppCode.CODE_ERROR_PASSWORD, Message.MSG_EN_ERROR_PASSWORD);
            }
        }
        McUserLoginRep response = mcUserDao.getUserByLogin(request.getMobile());
        //生成token
        response.setToken(JwtUtils.getToken());
        redisUtils.set(RedisUtils.redisGetKey(request.getMobile(), AppCode.REDIS_MOBILE_TOKEN), response.getToken(),
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
     *
     * @param userId
     * @param mobile
     * @param token
     * @param expireTime
     */
    @Override
    public void tokenRecord(long userId, String mobile, String token, Date expireTime) {
        Date updateTime = new Date();
        if (null == expireTime) {
            expireTime = DateUtils.getNextWeek(updateTime);
        }
        mcUserDao.saveLoginToken(userId, mobile, token, expireTime, updateTime);
    }

    /**
     * @param mobile
     */
    @Override public void logout(String mobile) {
        //注销token
        redisUtils.delete(RedisUtils.redisGetKey(mobile, AppCode.REDIS_MOBILE_TOKEN));
    }

    /**
     * 查询
     *
     * @param mobile
     *
     * @return
     */
    @Override public McUserInfoRep getUserInfo(String mobile) {
        return mcUserDao.getUserInfo(mobile);
    }

    /**
     * 修改
     *
     * @param request
     *
     * @return
     */
    @Override public McUserInfoRep updateUserInfo(McUserInfoReq request) {
        if (mcUserDao.updateUserInfo(request) >= Constants.ZERO) {
            return mcUserDao.getUserInfo(request.getMobile());//更新之后查询最新
        } else {
            throw new RRException(AppCode.EXCETPTION_DATABASE_FAIL, Message.MSG_EN_DATABASE);
        }
    }

}