package com.micro.boot.app.dao;

import com.micro.boot.app.object.request.user.McUserInfoReq;
import com.micro.boot.app.object.request.user.McUserRegisterReq;
import com.micro.boot.app.object.response.user.McUserInfoRep;
import com.micro.boot.app.object.response.user.McUserLoginRep;
import com.micro.boot.app.object.response.user.McUserRegisterRep;
import com.micro.boot.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Mc用户
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-10-23 21:23:54
 */
@Mapper
public interface McUserDao extends BaseDao<McUserInfoReq> {

    /**
     * 该手机是否已注册
     *
     * @param mobile
     *
     * @return
     */
    int existUserCount(String mobile);

    /**
     * 重复用户名数
     *
     * @param username
     *
     * @return
     */
    int isDupUsername(String username);


    /**
     * 注册用户
     *
     * @param request
     *
     * @return
     */
    long registerMcUser(McUserRegisterReq request);

    /**
     * 查询
     *
     * @param mobile
     *
     * @return
     */
    McUserRegisterRep getUserByMobile(String mobile);

    /**
     * 设置或修改密码
     * 根据mobile，更新password
     */
    int updatePasswordByMobile(@Param("mobile") String mobile, @Param("password") String password);

    /**
     * 查询 返回token
     *
     * @param mobile
     *
     * @return
     */
    McUserLoginRep getUserByLogin(String mobile);


    /**
     * 保存登录token
     */
    void saveLoginToken(@Param("userId") long userId, @Param("mobile") String mobile,
                        @Param("token") String token, @Param("expireTime") Date expireTime,
                        @Param("updateTime") Date updateTime);

    /**
     * 查询 基本信息
     *
     * @param mobile
     *
     * @return
     */
    McUserInfoRep getUserInfo(String mobile);


    /**
     * 修改
     * @param request
     * @return
     */
    int updateUserInfo(McUserInfoReq request);

}