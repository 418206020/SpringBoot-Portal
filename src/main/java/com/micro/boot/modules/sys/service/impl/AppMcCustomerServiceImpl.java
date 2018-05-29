package com.micro.boot.modules.sys.service.impl;

import com.micro.boot.modules.sys.dao.AppMcCustomerDao;
import com.micro.boot.modules.sys.entity.AppMcUserEntity;
import com.micro.boot.modules.sys.service.AppMcCustomerService;
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
@Service("appMcCustomerService")
public class AppMcCustomerServiceImpl implements AppMcCustomerService {
	@Autowired
	private AppMcCustomerDao appMcCustomerDao;

	@Override
	public AppMcUserEntity queryObject(Long id) {
		return appMcCustomerDao.queryObject(id);
	}

	@Override
	public List<AppMcUserEntity> queryList(Map<String, Object> map) {
		return appMcCustomerDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return appMcCustomerDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(AppMcUserEntity customer) {
		customer.setCreateTime(new Date());
		appMcCustomerDao.save(customer);
	}

	@Override
	@Transactional
	public void update(AppMcUserEntity customer) {
		appMcCustomerDao.update(customer);
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] ids) {
		appMcCustomerDao.deleteBatch(ids);
	}

	@Override
	public List<Long> queryRoleIdList(Long createUserId) {
		return appMcCustomerDao.queryRoleIdList(createUserId);
	}

}
