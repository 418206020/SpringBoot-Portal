package com.micro.boot.modules.sys.service.impl;

import com.micro.boot.modules.sys.dao.AppMcMsgDao;
import com.micro.boot.modules.sys.entity.AppMcMsgEntity;
import com.micro.boot.modules.sys.service.AppMcMsgService;
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
@Service("appMcMsgService")
public class AppMcMsgServiceImpl implements AppMcMsgService {
	@Autowired
	private AppMcMsgDao appMcMsgDao;

	@Override
	public AppMcMsgEntity queryObject(Long roleId) {
		return appMcMsgDao.queryObject(roleId);
	}

	@Override
	public List<AppMcMsgEntity> queryList(Map<String, Object> map) {
		return appMcMsgDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return appMcMsgDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(AppMcMsgEntity role) {
		appMcMsgDao.save(role);
	}

	@Override
	@Transactional
	public void update(AppMcMsgEntity role) {
		appMcMsgDao.update(role);
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] roleIds) {
		appMcMsgDao.deleteBatch(roleIds);
	}

	@Override
	public List<Long> queryRoleIdList(Long createMsgId) {
		return appMcMsgDao.queryRoleIdList(createMsgId);
	}

}
