package com.micro.boot.common;

/**
 * 〈API接口地址〉
 *
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
public class AppRestUrl {

    /**
     * 短信验证码
     */
    public static final String SMS_VERRIFY_CODE = "sms" + Constants.SEPPARATOR_SLASH + "{mobile}";

    /**
     * 手机号注册用户
     */
    public static final String REGISTER_MOBILE = "register" + Constants.SEPPARATOR_SLASH + "mobile";


}