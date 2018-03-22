package com.micro.boot.app.annotation;

import java.lang.annotation.*;

/**
 * api接口，忽略Token验证
 * @author huliang
 * @email yzcheng90@qq.com
 * @date 2018-01-23 15:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {

}
