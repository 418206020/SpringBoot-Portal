package com.micro.boot.app.object.request;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈用户注册对象实体〉
 *
 * @author Administrator
 * @create 2018/3/25
 * @since 1.0.0
 */
public class UserRegisterReq implements Serializable {
    private static final long serialVersionUID = 1L;


    //手机号
    private String mobile;

    //其他几种方式接入登录
    //wechat
    private String wechatId;
    //qq
    private String qqId;
    //alipay
    private String alipayId;

    //验证码
    private String verifyCode;

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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}