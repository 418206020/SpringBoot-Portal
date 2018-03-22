package com.micro.boot.app.annotation;

import java.lang.annotation.*;

/**
 * app登录效验
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017/9/23 14:30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
