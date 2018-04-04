package com.micro.boot.common;

/**
 * 〈APP接口返回码定义〉
 *
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
public class AppCode {

    /**
     * 正常
     */
    public final static int NORMAL = 0;

    /**
     * 成功返回
     */
    public final static int SUCCESS_RESPONSE = 200;


// ---------------- Exception 异常级-----------------------

    /**
     * 系统错误 服务器不能完成请求
     */
    public final static int EXCETPTION_FAIL = 500;

    /**
     * 系统错误 服务器不能完成请求
     */
    public final static int EXCETPTION_DATABASE_FAIL = 501;



// ---------------- Error 错误级-----------------------

    /**
     * 错误 服务器找不到给定的资源；文档不存在
     */
    public final static int ERROR_CODE_404 = 404;

    /**
     * token 为空
     */
    public final static int ERROR_CODE_401 = 401;
    /**
     * token 失效
     */
    public final static int ERROR_CODE_402 = 402;
    /**
     * mobile 空
     */
    public final static int ERROR_CODE_403 = 403;


// ---------------- BUSINESS 业务层-----------------------

    /**
     * 验证码 过期或失败
     */
    public final static int CODE_ERROR_VERIFY_CODE = 603;

    /**
     * 密码 错误
     */
    public final static int CODE_ERROR_PASSWORD = 602;

    /**
     * 非法手机号
     */
    public final static int CODE_MOBILE_ERROR = 601;

    /**
     * 查无此人
     */
    public final static int CODE_ERROR_USER = 604;

    /**
     * 非法输入
     */
    public final static int CODE_ERROR_INPUT = 606;



// ---------------- REDIS KEY TYPE-----------------------
    /**
     * 验证码
     */
    public final static String REDIS_VERIFY_CODE = "REDIS_VERIFY_CODE";

    /**
     * 验证码
     */
    public final static String REDIS_MOBILE_TOKEN = "REDIS_MOBILE_TOKEN";

}