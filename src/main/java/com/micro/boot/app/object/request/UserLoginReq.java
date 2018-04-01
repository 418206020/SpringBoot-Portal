package com.micro.boot.app.object.request;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈用户登录对象实体〉
 *
 * @author Administrator
 * @create 2018/3/25
 * @since 1.0.0
 */
public class UserLoginReq implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户id
    private Long id;
    //用户名
    private String userName;
    //手机号
    private String mobile;


    //其他几种方式接入登录
    //wechat
    private String wechatId;
    //qq
    private String qqId;
    //alipay
    private String alipayId;

    //密码
    private String password;
    //创建时间
    private Date createTime;
    //过期时间
    private Date expireTime;
    //过期时常
    private String expire;
//    //token
//    private String token;

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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
}