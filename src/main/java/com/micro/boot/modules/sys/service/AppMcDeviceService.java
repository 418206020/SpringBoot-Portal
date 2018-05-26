package com.micro.boot.modules.sys.service;

import com.micro.boot.modules.sys.entity.AppMcDeviceEntity;

import java.util.List;
import java.util.Map;


/**
 * 角色
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2016年9月18日 上午9:42:52
 */
public interface AppMcDeviceService {
	
	AppMcDeviceEntity queryObject(Long roleId);
	
	List<AppMcDeviceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AppMcDeviceEntity role);
	
	void update(AppMcDeviceEntity role);
	
	void deleteBatch(Long[] roleIds);

	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createDeviceId);
}
