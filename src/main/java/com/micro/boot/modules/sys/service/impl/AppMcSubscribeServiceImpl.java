package com.micro.boot.modules.sys.service.impl;

import com.micro.boot.modules.sys.dao.AppMcSubscribeDao;
import com.micro.boot.modules.sys.entity.AppMcSubscribeEntity;
import com.micro.boot.modules.sys.service.AppMcSubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 用户与角色对应关系
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2016年9月18日 上午9:45:48
 */
@Service("appMcSubscribeService")
public class AppMcSubscribeServiceImpl implements AppMcSubscribeService {
	@Autowired
	private AppMcSubscribeDao appMcSubscribeDao;

	@Override
	public AppMcSubscribeEntity queryObject(Long roleId) {
		return appMcSubscribeDao.queryObject(roleId);
	}

	@Override
	public List<AppMcSubscribeEntity> queryList(Map<String, Object> map) {
		return appMcSubscribeDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return appMcSubscribeDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(AppMcSubscribeEntity role) {
		appMcSubscribeDao.save(role);
	}

	@Override
	@Transactional
	public void update(AppMcSubscribeEntity role) {
		appMcSubscribeDao.update(role);
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] roleIds) {
		appMcSubscribeDao.deleteBatch(roleIds);
	}

	@Override
	public List<Long> queryRoleIdList(Long createSubscribeId) {
		return appMcSubscribeDao.queryRoleIdList(createSubscribeId);
	}

}
