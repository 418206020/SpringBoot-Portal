/**
 * Copyright (C), 2018
 * FileName: MobileToken
 * Author:   Administrator
 * Date:     2018/4/5 1:15
 * Description: token 拦截
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.micro.boot.app.annotation;
/**
 * Created by 418206020 on 2018/4/5.
 */

import java.lang.annotation.*;

/**
 * 〈token 拦截〉
 *
 * @author Administrator
 * @create 2018/4/5
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MobileToken {

}
