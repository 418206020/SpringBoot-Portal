package com.micro.boot.thirdparty.ucpaas.template;

/**
 * 〈Ucpaas发送短信内容模板〉
 *
 * @author Administrator
 * @create 2018/3/24
 * @since 1.0.0
 */
public class UcpaasSms {

    //用户的账号唯一标识“Account Sid”，在开发者控制台获取
    public String sid;

    //用户密钥，在开发者控制台获取
    public String token;

    //创建应用时系统分配的唯一标示
    public String appid;

    //创建短信模板时系统分配的唯一标示
    public String templateid;

    //模板中的替换参数，如该模板不存在参数则无需传该参数或者参数为空，如果有多个参数则需要写在同一个字符串中，
    // 以英文逗号分隔 （如：“a,b,c”），参数中不能含有特殊符号“【】”和“,”
    public String param;

    //接收的单个手机号，暂仅支持国内号码
    public String mobile;

    //选填 用户透传ID，随状态报告返回
    public String uid;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTemplateid() {
        return templateid;
    }

    public void setTemplateid(String templateid) {
        this.templateid = templateid;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}