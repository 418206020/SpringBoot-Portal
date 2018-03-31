package com.micro.boot.app.object;

import com.micro.boot.common.Constants;

import java.util.HashMap;

/**
 * 〈请求头信息〉
 *
 * @author Administrator
 * @create 2018/3/31
 * @since 1.0.0
 */
public class CommonHeaders {


    /**
     * 定义header中公共key
     */
    private static final String KEY_TOKEN = "token";
    private static final String KEY_CONTENT_LENGTH = "Content-Length";
    private static final String KEY_CONTENT_TYPE = "Content-Type";
    private static final String KEY_ACCEPT_LANGUAGE = "Accept-Language";
    private static final String KEY_ACCEPT_CHARSET = "Accept-Charset";
    private static final String KEY_AUTH_MODE = "Auth-Mode";
    private static final String KEY_BELONGID = "BelongId";
    private static final String KEY_OPERATOR = "Operator";

    private static HashMap<String, Object> headMap = new HashMap<>();

    /**
     * 构造默认信息
     */
    public CommonHeaders() {
        headMap.put(KEY_CONTENT_TYPE, Constants.APPLICATION_JSON);
        headMap.put(KEY_ACCEPT_CHARSET, Constants.CHARSET_NAME);//utf-8
        headMap.put(KEY_ACCEPT_LANGUAGE, Constants.LANG_ZH_CN);
    }

    /**
     * 重定义header的getter和setter
     */
    public String getToken() {
        return (String) headMap.get(KEY_TOKEN);
    }

    public void setToken(String token) {
        this.headMap.put(KEY_TOKEN, token);
    }

    public String getContentType() {
        return (String) headMap.get(KEY_CONTENT_TYPE);
    }

    public void setContentType(String contentType) {
        this.headMap.put(KEY_CONTENT_TYPE, contentType);
    }

    public String getContentLength() {
        return (String) headMap.get(KEY_CONTENT_LENGTH);
    }

    public void setContentLength(String contentLength) {
        this.headMap.put(KEY_CONTENT_LENGTH, contentLength);
    }

    public String getAcceptLanguage() {
        return (String) headMap.get(KEY_ACCEPT_LANGUAGE);
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.headMap.put(KEY_ACCEPT_LANGUAGE, acceptLanguage);
    }

    public String getAcceptCharset() {
        return (String) headMap.get(KEY_ACCEPT_CHARSET);
    }

    public void setAcceptCharset(String acceptCharset) {
        this.headMap.put(KEY_ACCEPT_CHARSET, acceptCharset);
    }

    public String getAuthMode() {
        return (String) headMap.get(KEY_AUTH_MODE);
    }

    public void setAuthMode(String authMode) {
        this.headMap.put(KEY_AUTH_MODE, authMode);
    }

    public String getBelongId() {
        return (String) headMap.get(KEY_BELONGID);
    }

    public void setBelongId(String belongId) {
        this.headMap.put(KEY_BELONGID, belongId);
    }

    public String getOperator() {
        return (String) headMap.get(KEY_OPERATOR);
    }

    public void setOperator(String operator) {
        this.headMap.put(KEY_OPERATOR, operator);
    }
}