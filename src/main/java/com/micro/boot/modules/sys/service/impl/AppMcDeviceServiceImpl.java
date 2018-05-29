package com.micro.boot.modules.sys.service.impl;

import com.micro.boot.modules.sys.dao.AppMcDeviceDao;
import com.micro.boot.modules.sys.entity.AppMcDeviceEntity;
import com.micro.boot.modules.sys.service.AppMcDeviceService;
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
@Service("appMcDeviceService")
public class AppMcDeviceServiceImpl implements AppMcDeviceService {
	@Autowired
	private AppMcDeviceDao appMcDeviceDao;

	@Override
	public AppMcDeviceEntity queryObject(Long id) {
		return appMcDeviceDao.queryObject(id);
	}

	@Override
	public List<AppMcDeviceEntity> queryList(Map<String, Object> map) {
		return appMcDeviceDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return appMcDeviceDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(AppMcDeviceEntity device) {
		device.setCreateTime(new Date());
		appMcDeviceDao.save(device);
	}

	@Override
	@Transactional
	public void update(AppMcDeviceEntity device) {
		appMcDeviceDao.update(device);
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] deviceIds) {
		appMcDeviceDao.deleteBatch(deviceIds);
	}

	@Override
	public List<Long> queryRoleIdList(Long createDeviceId) {
		return appMcDeviceDao.queryRoleIdList(createDeviceId);
	}

}
