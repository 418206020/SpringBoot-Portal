/**
 * Copyright (C), 2018
 * FileName: McRegisterService
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


import com.micro.boot.app.object.request.McUserRegisterReq;
import com.micro.boot.app.object.response.McUserRegisterRep;

/**
 * 〈注册〉
 *
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
public interface McRegisterService {

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    public String sendSmsVerifyCode(String mobile);

    /**
     * 注册用户
     * @param request
     * @return
     */
    public McUserRegisterRep registerUser(McUserRegisterReq request);

    /**
     * 用户名是否存在
     * @param username
     * @return
     */
    public Boolean isDupUsername(String username);

}
