package com.micro.boot.interceptor;


import com.micro.boot.app.annotation.MobileToken;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Message;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.common.utils.Tools;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2018-01-23 15:38
 */
@Component
public class MobileTokenInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final String HEARD_TOKEN = "token";
    public static final String HEARD_MOBILE = "mobile";
    @Resource
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception
    {

        MobileToken annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(MobileToken.class);
        }else{
            // 如果不是映射到方法直接通过
            return true;
        }

        if(annotation == null){
            return true;
        }
        logger.info("MobileTokenInterceptor start...");
        //获取用户凭证
        String token = request.getHeader(HEARD_TOKEN);
        String mobile = request.getHeader(HEARD_MOBILE);

        //token
        if (StringUtils.isEmpty(token)) {
            logger.error("MobileTokenInterceptor::Token null");
            throw new RRException(AppCode.ERROR_CODE_401, Message.MSG_EN_HEAD_TOKEN_NULL);
        }
        //mobile
        if (StringUtils.isEmpty(mobile)) {
            logger.error("MobileTokenInterceptor::Mobile null");
            throw new RRException(AppCode.ERROR_CODE_403, Message.MSG_EN_HEAD_MOBILE);
        } else if (!Tools.checkMobileNumber(mobile)) {
            throw new RRException(AppCode.CODE_MOBILE_ERROR, Message.MSG_EN_ERROR_MOBILE);
        }

        if (!token.equals(redisUtils.get(RedisUtils.redisSetKey(mobile, AppCode.REDIS_MOBILE_TOKEN)))) {
            logger.error("MobileTokenInterceptor::Mobile=" + mobile + "Token=" + token);
            throw new RRException(AppCode.ERROR_CODE_402, Message.MSG_EN_HEAD_TOKEN_INVALID);
        }
        logger.info("MobileTokenInterceptor end...");
        return true;
    }
}
