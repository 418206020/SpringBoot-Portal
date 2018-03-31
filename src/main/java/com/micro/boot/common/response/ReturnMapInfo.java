package com.micro.boot.common.response;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据-主要用作前端展现扩展
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2016年10月27日 下午9:59:27
 */
public class ReturnMapInfo extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public ReturnMapInfo() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static ReturnMapInfo error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static ReturnMapInfo error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static ReturnMapInfo error(int code, String msg) {
		ReturnMapInfo returnMapInfo = new ReturnMapInfo();
		returnMapInfo.put("code", code);
		returnMapInfo.put("msg", msg);
		return returnMapInfo;
	}

	public static ReturnMapInfo ok(String msg) {
		ReturnMapInfo returnMapInfo = new ReturnMapInfo();
		returnMapInfo.put("msg", msg);
		return returnMapInfo;
	}
	
	public static ReturnMapInfo ok(Map<String, Object> map) {
		ReturnMapInfo returnMapInfo = new ReturnMapInfo();
		returnMapInfo.putAll(map);
		return returnMapInfo;
	}
	
	public static ReturnMapInfo ok() {
		return new ReturnMapInfo();
	}

	public ReturnMapInfo put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
