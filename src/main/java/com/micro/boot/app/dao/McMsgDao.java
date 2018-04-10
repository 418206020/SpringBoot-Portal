package com.micro.boot.app.dao;

import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-10-23 21:23:54
 */
@Mapper
public interface McMsgDao extends BaseDao<McMsgReq> {


}