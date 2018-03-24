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


    public static final String URL_HTTPS = "https://";
    public static final String UCPAAS_CONFIG = "ucpaas.config";

    public static final String WEB_INF_CLASSES = "/WEB-INF/classes";
    public static final String PROP_CONFIG = "/config.properties";

    public static final String CONTENT_TYPE = "application/json;charset=utf-8";
    public static final String ACCEPT = "application/json";


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
     * RESTFUL接口USER模块APP访问前缀
     */
    public static final String APP = "/app" + SEPPARATOR_SLASH + VERSION_APP;


    /**
     * USER 模块
     */
    public static final String REST_MODULE_USER = APP + "user";
    

}