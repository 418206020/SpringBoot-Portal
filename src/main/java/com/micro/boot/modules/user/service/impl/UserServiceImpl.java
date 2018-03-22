package com.micro.boot.modules.user.service.impl;

import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.validator.Assert;
import com.micro.boot.modules.user.dao.UserDao;
import com.micro.boot.modules.user.entity.UserEntity;
import com.micro.boot.modules.user.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserEntity queryObject(Long userId){
		return userDao.queryObject(userId);
	}
	
	@Override
	public List<UserEntity> queryList(Map<String, Object> map){
		return userDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userDao.queryTotal(map);
	}

	@Override
	public void save(UserEntity userEntity){
		userEntity.setPassword(DigestUtils.sha256Hex(userEntity.getPassword()));
		userEntity.setCreateTime(new Date());
		userDao.save(userEntity);
	}

	@Override
	public void save(String mobile, String password) {
		UserEntity userEntity = new UserEntity();
		userEntity.setMobile(mobile);
		userEntity.setPassword(password);
		userEntity.setPassword(DigestUtils.sha256Hex(userEntity.getPassword()));
		userEntity.setCreateTime(new Date());
		userDao.save(userEntity);
	}

	@Override
	public void update(UserEntity user){
		userDao.update(user);
	}
	
	@Override
	public void delete(Long userId){
		userDao.delete(userId);
	}
	
	@Override
	public void deleteBatch(Long[] userIds){
		userDao.deleteBatch(userIds);
	}

	@Override
	public UserEntity queryByMobile(String mobile) {
		return userDao.queryByMobile(mobile);
	}

	@Override
	public long login(String mobile, String password) {
		UserEntity user = queryByMobile(mobile);
		Assert.isNull(user, "用户不存在");

		//密码错误
		String userpassword = DigestUtils.sha256Hex(password);
		if(!user.getPassword().equals(userpassword)){
			throw new RRException("密码错误");
		}

		return user.getUserId();
	}

}
