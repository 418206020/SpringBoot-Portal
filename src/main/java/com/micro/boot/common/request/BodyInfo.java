package com.micro.boot.common.request;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.micro.boot.common.Constants;
import com.micro.boot.common.utils.CDESCrypt;
import com.micro.boot.common.utils.Tools;
import org.apache.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @category app接收
 * @author huliang
 * 2017-04-25
 */
public class BodyInfo<T> implements Serializable {

	private int code = 0;
	private String data = "";
	private String version = Constants.VERSION_APP;

	public final static int APP = 0;

	private final static String KEY = Constants.CDES_KEY_8BIT;

	/**
	 * 重载构建消息体
	 * @param version
	 * @param object
	 * @return
	 */
	public static BodyInfo build(String version, Object object) {
		BodyInfo returnAppInfo = new BodyInfo();
		returnAppInfo.setEncryptData(object);
		returnAppInfo.setVersion(version);
		returnAppInfo.setCode(APP);
		return returnAppInfo;
	}

	/**
	 * 完整构建消息体
	 * @param code
	 * @param version
	 * @param object
	 * @return
	 */
	public static BodyInfo build(int code, String version, Object object) {
		BodyInfo bodyInfo = build(version,object);
		bodyInfo.setCode(code);
		return bodyInfo;
	}


	public int getCode() {
		return code;
	}

	public BodyInfo setCode(int status) {
		this.code = status;
		return this;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	private HashMap<String, Object> decryptData(String data) {
		String mData = null;
		if (!Tools.isEmpty(data)) {
			try {
				mData = CDESCrypt.decryptString(data, KEY);
				//mData=data;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new Gson().fromJson(mData, new TypeToken<HashMap<String, Object>>() {
		}.getType());
	}

	/**
	 * 解密数据
	 * @return
	 */
	public String decryptData() {
		String mData = null;
		if (!Tools.isEmpty(this.data)) {
			try {
				mData = CDESCrypt.decryptString(this.data, KEY);
				//mData=this.data;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mData;
	}

	public BodyInfo<T> setJsonData(T t) {
		String mData = new Gson().toJson(t);
		this.data = mData;
		return this;
	}

	public BodyInfo setEncryptData(T t) {
		String mData = new Gson().toJson(t);
		try {
			if (!Tools.isEmpty(mData)) {
				this.data = CDESCrypt.encryptString(mData, KEY);
				//this.data=mData;
			} else {
				this.data = mData;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public String getVersion() {
		return version;
	}

	public BodyInfo setVersion(String version) {
		this.version = version;
		return this;
	}

	/**
	 * Json数据构造
	 * @return
	 */
	@Override
	public String toString() {
		return "{" +
				"code='" + code + '\'' +
				", data='" + data + '\'' +
				", version='" + version + '\'' +
				'}';
	}

}
