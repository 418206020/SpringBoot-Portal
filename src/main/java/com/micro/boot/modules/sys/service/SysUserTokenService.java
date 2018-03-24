package com.micro.boot.modules.sys.service;

import com.micro.boot.common.utils.RequestInfo;
import com.micro.boot.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2018-01-23 15:22:07
 */
public interface SysUserTokenService {

	SysUserTokenEntity queryByUserId(Long userId);

	void save(SysUserTokenEntity token);
	
	void update(SysUserTokenEntity token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	RequestInfo createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
