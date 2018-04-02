/**
 * Copyright (C), 2018
 * FileName: RegisterService
 * Author:   Administrator
 * Date:     2018/4/1 16:03
 * Description: 注册
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.micro.boot.app.service.user;
/**
 * Created by 418206020 on 2018/4/1.
 */


import com.micro.boot.app.object.request.PasswordRestReq;
import com.micro.boot.app.object.request.UserRegisterReq;
import com.micro.boot.app.object.response.UserRegisterRep;

/**
 * 〈用户管理〉
 *
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
public interface UserService {


    /**
     * 密码重置成默认
     * @param mobile
     */
    void passwordReset(String mobile);

    /**
     * 修改新密码
     * @param request
     */
    void passwordReset(PasswordRestReq request);


}
