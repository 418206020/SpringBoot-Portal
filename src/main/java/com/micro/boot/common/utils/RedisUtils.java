package com.micro.boot.common.utils;

import com.google.gson.Gson;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Message;
import com.micro.boot.common.exception.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-07-17 21:12
 */
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    /**
     * 默认过期时长1天，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;

    public final static long NOT_EXPIRE = -1;

    /**
     * TODO TEST
     */
    public final static long EXPIRE_TEST = 60 * 60 * 24 * 365;
    /**
     * /**
     * 60秒
     */
    public final static long EXPIRE_60S = 60;

    public final static String verify = "!@#$%%$#@!";
    /**
     * 7天
     */
    public final static long EXPIRE_7DAYS = 60 * 60 * 24 * 7;


    private final static Gson gson = new Gson();

    /**
     * 使用MD5作为key
     *
     * @param key
     *
     * @return
     */
    private static String md5Key(String key) {
        return MD5.md5(key);
    }

    /**
     * redisGetKey
     *
     * @return
     */
    public static String redisGetKey(String key, String type) {
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(type)) {
            return MD5.md5(type.concat(verify).concat(key));
        } else {
            throw new RRException(AppCode.EXCETPTION_FAIL, Message.MSG_EN_ERROR_SYSTEM);
        }
    }

    /**
     * redisSetKey
     *
     * @return
     */
    public static String redisSetKey(String key, String type) {
        if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(type)) {
            return type.concat(verify).concat(key);
        } else {
            throw new RRException(AppCode.EXCETPTION_FAIL, Message.MSG_EN_ERROR_SYSTEM);
        }
    }

    /* set key after redisSetKey
     */
    public void set(String key, Object value, long expire) {
        valueOperations.set(md5Key(key), toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(md5Key(key), expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    /* get key after redisGetKey
     */
    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    /* get key after redisGetKey
     */
    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    /* delete  key after redisGetKey
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String)
        {
            return String.valueOf(object);
        }
        return gson.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
