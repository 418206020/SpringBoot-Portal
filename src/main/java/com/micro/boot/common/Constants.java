/**
 * Created by 418206020 on 2018-03-21.
 */

package com.micro.boot.common;

/**
 * 〈系统常量〉
 *
 * @author Administrator
 * @create 2018/3/23
 * @since 1.0.0
 */
public class Constants {

    /**
     * 系统错误 服务器不能完成请求
     */
    public final static int EXCETPTION_SYSTEM = 500;

    /**
     * http相关
     */

    public static final String URL_HTTPS = "https://";
    public static final String UCPAAS_CONFIG = "ucpaas.config";

    /**
     * 配置
     */
    public static final String WEB_INF_CLASSES = "/WEB-INF/classes";
    public static final String PROP_CONFIG = "/config.properties";


    /**
     * rest json
     */
    public static final String APPLICATION_JSON_UTF8 = "application/json;charset=utf-8";
    public static final String APPLICATION_JSON = "application/json";


    /**
     * 语言
     */
    public static final String LANG_ZH_CN = "zh-cn";
    public static final String LANG_ZH_EN = "zh-en";
    public static final String LANG_ZH_TW = "zh-tw";

    /**
     * 默认编码
     */
    public static final String CHARSET_NAME = "UTF-8";

    /**
     * APP发布版本
     */
    public static final String VERSION_APP = "v1.0.0";

    /**
     * WEB版本
     */
    public static final String VERSION_WEB = "v1.0.0";

    public static final String SEPPARATOR_DOT = ".";
    /**
     * 斜杠，分隔符
     */
    public static final String SEPPARATOR_SLASH = "/";

    /**
     * 反斜杠，分隔符
     */
    public static final String SEPPARATOR_BACKSLASH = "\\";

    /**
     * 反斜杠，分隔符
     */
    public static final int COUNT_SALT = 20;



    /**
     * RESTFUL接口USER模块APP访问前缀
     */
    public static final String APP = "/app" + SEPPARATOR_SLASH + VERSION_APP;


    /**
     * 对称8倍秘钥，可增加秘钥长度增加破解复杂度
     */
    public static final String CDES_KEY_8BIT = "O0OO00O0";
    

}