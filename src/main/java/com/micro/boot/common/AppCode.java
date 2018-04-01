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



// ---------------- Error 错误级-----------------------

    /**
     * 错误 服务器找不到给定的资源；文档不存在
     */
    public final static int ERROR_CODE_404 = 404;




// ---------------- BUSINESS 业务层-----------------------

    /**
     * token 失效
     */
    public final static int CODE_TOKEN_FAIL = 401;

    /**
     * 密码 错误
     */
    public final static int CODE_ERROR_PASSWORD = 502;

    /**
     * 非法手机号
     */
    public final static int CODE_MOBILE_ERROR = 601;

}