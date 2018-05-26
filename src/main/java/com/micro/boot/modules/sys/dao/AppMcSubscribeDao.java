package com.micro.boot.modules.sys.dao;

import com.micro.boot.modules.sys.entity.AppMcSubscribeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色管理
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2016年9月18日 上午9:33:33
 */
@Mapper
public interface AppMcSubscribeDao extends BaseDao<AppMcSubscribeEntity> {

	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
