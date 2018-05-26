package com.micro.boot.modules.sys.service;

import com.micro.boot.modules.sys.entity.AppMcUserEntity;
import com.micro.boot.modules.sys.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;


/**
 * 角色
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2016年9月18日 上午9:42:52
 */
public interface AppMcCustomerService {
	
	AppMcUserEntity queryObject(Long roleId);
	
	List<AppMcUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AppMcUserEntity role);
	
	void update(AppMcUserEntity role);
	
	void deleteBatch(Long[] roleIds);

	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
