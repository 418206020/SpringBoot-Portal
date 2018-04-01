package com.micro.boot.app.object.response;

import java.io.Serializable;

/**
 * 〈用户注册对象实体〉
 * 注册后可直接使用返回token登录
 *
 * @author Administrator
 * @create 2018/3/25
 * @since 1.0.0
 */
public class UserRegisterRep implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户id
    private Long userId;

    //手机号
    private String mobile;

    //其他几种方式接入登录
    //wechat
    private String wechatId;
    //qq
    private String qqId;
    //alipay
    private String alipayId;

    //token
    private String token;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId;
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}