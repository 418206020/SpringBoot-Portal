package com.micro.boot.app.object.request.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈退出登录〉
 *
 * @author Administrator
 * @create 2018/3/25
 * @since 1.0.0
 */
public class McUserLogoutReq implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户id
    private Long id;
    //用户名
    private String username;
    //手机号
    private String mobile;
    //密码
    private String password;
    //创建时间
    private Date createTime;
    //过期时间
    private Date expireTime;
    //token
    private String token;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}