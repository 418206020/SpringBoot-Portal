package com.micro.boot.app.dao;

import com.micro.boot.app.object.request.UserLoginBean;
import com.micro.boot.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-10-23 21:23:54
 */
@Mapper
public interface UserLoginDao extends BaseDao<UserLoginBean> {

    UserLoginBean queryByMobile2(String mobile);
}
