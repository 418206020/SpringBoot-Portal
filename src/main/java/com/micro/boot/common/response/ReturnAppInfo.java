package com.micro.boot.common.response;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Constants;
import com.micro.boot.common.utils.CDESCrypt;
import com.micro.boot.common.utils.Tools;
import org.apache.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;

import static jdk.nashorn.tools.Shell.SUCCESS;

/**
 * @author huliang
 * 2017-04-25
 * @category app返回类
 */
public class ReturnAppInfo<T> implements Serializable {

    private int code = AppCode.EXCETPTION_FAIL;//默认500
    private String message = "";
    private String data = "";
    private String version = Constants.VERSION_APP;

    private final static String KEY = Constants.CDES_KEY_8BIT;

    //-------------------------- success start -------------------------------

    /**
     * 加密数据
     * @param object
     * @return
     */
    public static ReturnAppInfo successEncrypt(Object object) {
        ReturnAppInfo returnAppInfo = success();
        returnAppInfo.setEncryptData(object);
        return returnAppInfo;
    }

    /**
     * 不加密
     * @return
     */
    public static ReturnAppInfo success() {
        ReturnAppInfo returnAppInfo = new ReturnAppInfo();
        returnAppInfo.setCode(SUCCESS);
        returnAppInfo.setMessage("请求成功");
        return returnAppInfo;
    }
    //-------------------------- success end ----------------------------------

    //-------------------------- error start ----------------------------------
    public static ReturnAppInfo error(int code, String msg) {
        ReturnAppInfo returnAppInfo = new ReturnAppInfo();
        returnAppInfo.setCode(code);
        returnAppInfo.setMessage(msg);
        return returnAppInfo;
    }
    public static ReturnAppInfo error(String msg) {
        ReturnAppInfo returnAppInfo = new ReturnAppInfo();
        returnAppInfo.setCode(AppCode.EXCETPTION_FAIL);
        returnAppInfo.setMessage(msg);
        return returnAppInfo;
    }
    public static ReturnAppInfo error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }
    //-------------------------- error end -------------------------------------

    public int getCode() {
        return code;
    }

    public ReturnAppInfo setCode(int status) {
        this.code = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ReturnAppInfo setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public HashMap<String, Object> decryptData(String data) {
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

    public ReturnAppInfo setJsonData(T t) {
        String mData = new Gson().toJson(t);
        this.data = mData;
        return this;
    }

    public ReturnAppInfo setEncryptData(T t) {
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

    public ReturnAppInfo setVersion(String version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
