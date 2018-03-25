package com.micro.boot.common.exception;

import com.micro.boot.common.response.ReturnAppInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
public class RRExceptionHandler extends ReturnAppInfo {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public ReturnAppInfo handleRRException(RRException e){
		ReturnAppInfo returnAppInfo = new ReturnAppInfo();
		returnAppInfo.setCode(e.getCode());
		returnAppInfo.setMessage(e.getMessage());
		return returnAppInfo;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ReturnAppInfo handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return ReturnAppInfo.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public ReturnAppInfo handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return ReturnAppInfo.error("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public ReturnAppInfo handleException(Exception e){
		logger.error(e.getMessage(), e);
		return ReturnAppInfo.error();
	}
}
