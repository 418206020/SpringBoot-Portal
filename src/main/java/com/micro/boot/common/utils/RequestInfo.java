package com.micro.boot.common.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2016年10月27日 下午9:59:27
 */
public class RequestInfo extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public RequestInfo() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static RequestInfo error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static RequestInfo error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static RequestInfo error(int code, String msg) {
		RequestInfo requestInfo = new RequestInfo();
		requestInfo.put("code", code);
		requestInfo.put("msg", msg);
		return requestInfo;
	}

	public static RequestInfo ok(String msg) {
		RequestInfo requestInfo = new RequestInfo();
		requestInfo.put("msg", msg);
		return requestInfo;
	}
	
	public static RequestInfo ok(Map<String, Object> map) {
		RequestInfo requestInfo = new RequestInfo();
		requestInfo.putAll(map);
		return requestInfo;
	}
	
	public static RequestInfo ok() {
		return new RequestInfo();
	}

	public RequestInfo put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
