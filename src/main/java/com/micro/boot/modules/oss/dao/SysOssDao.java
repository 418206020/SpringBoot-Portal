package com.micro.boot.modules.oss.dao;

import com.micro.boot.modules.oss.entity.SysOssEntity;
import com.micro.boot.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件上传
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2018-01-25 12:13:26
 */
@Mapper
public interface SysOssDao extends BaseDao<SysOssEntity> {
	
}
