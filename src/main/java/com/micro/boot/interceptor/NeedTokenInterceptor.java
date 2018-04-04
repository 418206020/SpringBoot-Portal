package com.micro.boot.interceptor;


import com.micro.boot.app.annotation.MobileToken;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Message;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.utils.RedisUtils;
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
public class NeedTokenInterceptor extends HandlerInterceptorAdapter {

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
//        logger.info("NeedTokenInterceptor start...");
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(MobileToken.class);
        } else {
            return true;
        }

        if (annotation == null) {
            return true;
        }

        //获取用户凭证
        String token = request.getHeader(HEARD_TOKEN);
        String mobile = request.getHeader(HEARD_MOBILE);

        //token
        if (StringUtils.isEmpty(token)) {
            logger.error("NeedTokenInterceptor::Token null");
            throw new RRException(AppCode.ERROR_CODE_401, Message.MSG_EN_HEAD_TOKEN_NULL);
        }
        //mobile
        if (StringUtils.isEmpty(mobile)) {
            logger.error("NeedTokenInterceptor::Mobile null");
            throw new RRException(AppCode.ERROR_CODE_403, Message.MSG_EN_HEAD_MOBILE);
        }

        if (!token.equals(redisUtils.get(RedisUtils.redisSetKey(mobile, AppCode.REDIS_MOBILE_TOKEN)))) {
            logger.error("NeedTokenInterceptor::Mobile=" + mobile + "Token=" + token);
            throw new RRException(AppCode.ERROR_CODE_402, Message.MSG_EN_HEAD_TOKEN_INVALID);
        }
        return true;
    }
}
