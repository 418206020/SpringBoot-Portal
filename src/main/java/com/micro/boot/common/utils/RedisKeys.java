package com.micro.boot.common.utils;

/**
 * Redis所有Keys
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-07-18 19:51
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
