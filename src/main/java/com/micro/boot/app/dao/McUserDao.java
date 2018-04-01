package com.micro.boot.app.dao;

import com.micro.boot.app.object.request.UserLoginReq;
import com.micro.boot.app.object.request.UserRegisterReq;
import com.micro.boot.app.object.response.UserRegisterRep;
import com.micro.boot.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mc用户
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-10-23 21:23:54
 */
@Mapper
public interface McUserDao extends BaseDao<UserLoginReq> {

    /**
     * 该手机是否已注册
     *
     * @param mobile
     *
     * @return
     */
    boolean existUserByMobile(String mobile);


    /**
     * 注册用户
     * @param request
     * @return
     */
    UserRegisterRep registerMcUser(UserRegisterReq request);


}