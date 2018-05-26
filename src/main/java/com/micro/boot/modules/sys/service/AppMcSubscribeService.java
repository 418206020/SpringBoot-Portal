package com.micro.boot.modules.sys.service;

import com.micro.boot.modules.sys.entity.AppMcSubscribeEntity;

import java.util.List;
import java.util.Map;


/**
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2016年9月18日 上午9:42:52
 */
public interface AppMcSubscribeService {
	
	AppMcSubscribeEntity queryObject(Long roleId);
	
	List<AppMcSubscribeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AppMcSubscribeEntity role);
	
	void update(AppMcSubscribeEntity role);
	
	void deleteBatch(Long[] roleIds);

	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createSubscribeId);
}
