package com.micro.boot.common;

/**
 * 〈API接口地址〉
 *
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
public class AppRestUrl {

    //--------------------------------------------------注册用户----分隔符----------------------------------------------------
    /**
     * 短信验证码
     */
    public static final String SMS_VERRIFY_CODE = "sms" + Constants.SEPPARATOR_SLASH + "{mobile}";

    /**
     * 手机号注册用户
     */
    public static final String REGISTER_MOBILE = "register" + Constants.SEPPARATOR_SLASH + "mobile";

    /**
     * 修改密码
     */
    public static final String PASSWORD_RESET = "password" + Constants.SEPPARATOR_SLASH + "reset";


    /**
     * 重置密码
     */
    public static final String PASSWORD_RESET_DEFAULT = PASSWORD_RESET + Constants.SEPPARATOR_SLASH + "default";

    /**
     * 登录
     */
    public static final String LOGIN_PWD_VERIFYCODE = "login";

    /**
     * 退出登录
     */
    public static final String LOGOUT = "logout";

    /**
     * 查询个人基本信息
     */
    public static final String MC_USER_INFO = "info";

    /**
     * 修改个人基本信息
     */
    public static final String MC_USER_UPDATE = "info" + Constants.SEPPARATOR_SLASH + "update";

    //--------------------------------------------------设备----分隔符----------------------------------------------------
    /**
     * ADD
     */
    public static final String MC_DEVICE_ADD = "info" + Constants.SEPPARATOR_SLASH + "add";

    /**
     * EDIT
     */
    public static final String MC_DEVICE_EDIT = "info" + Constants.SEPPARATOR_SLASH + "edit";

    /**
     * GET
     */
    public static final String MC_DEVICE_GET = "info" + Constants.SEPPARATOR_SLASH + "{macId}";

    /**
     * DEL
     */
    public static final String MC_DEVICE_DEL = "info" + Constants.SEPPARATOR_SLASH + "del";

    /**
     * LIST
     */
    public static final String MC_DEVICE_LIST = "info" + Constants.SEPPARATOR_SLASH + "list";


    //--------------------------------------------------队列----分隔符----------------------------------------------------



}