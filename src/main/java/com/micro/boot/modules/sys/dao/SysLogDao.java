package com.micro.boot.modules.sys.dao;

import com.micro.boot.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2018-01-08 10:40:56
 */
@Mapper
public interface SysLogDao extends BaseDao<SysLogEntity> {
	
}
