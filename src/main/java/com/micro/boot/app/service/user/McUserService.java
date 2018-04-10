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


import com.micro.boot.app.object.request.user.McPasswordResetReq;
import com.micro.boot.app.object.request.user.McUserInfoReq;
import com.micro.boot.app.object.request.user.McUserLoginReq;
import com.micro.boot.app.object.response.user.McUserInfoRep;
import com.micro.boot.app.object.response.user.McUserLoginRep;

import java.util.Date;

/**
 * 〈用户管理〉
 *
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
public interface McUserService {


    /**
     * 密码重置成默认
     * @param mobile
     */
    void passwordReset(String mobile);

    /**
     * 修改新密码
     * @param request
     */
    void passwordReset(McPasswordResetReq request, String token);


    /**
     * 登录
     * @param request
     * @return
     */
    McUserLoginRep loginByPasswordOrVerifyCode(McUserLoginReq request);


    /**
     * 记录用户最新token 暂时不用
     * @param userId
     * @param mobile
     * @param token
     * @param expireTime
     */
    void tokenRecord(long userId, String mobile, String token, Date expireTime);

    /**
     * 退出登录
     * @param mobile
     */
    void logout(String mobile);

    /**
     *
     * @param mobile
     * @return
     */
    McUserInfoRep getUserInfo(String mobile);

    /**
     *
     * @param request
     * @return
     */
    McUserInfoRep updateUserInfo(McUserInfoReq request);
}
