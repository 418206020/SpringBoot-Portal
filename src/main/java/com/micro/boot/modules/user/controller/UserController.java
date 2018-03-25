package com.micro.boot.modules.user.controller;

import com.micro.boot.common.response.ReturnMapInfo;
import com.micro.boot.common.utils.PageUtils;
import com.micro.boot.common.utils.Query;
import com.micro.boot.modules.user.entity.UserEntity;
import com.micro.boot.modules.user.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 用户
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-10-23 21:23:54
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("user:user:list")
	public ReturnMapInfo list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		query.isPaging(true);
		List<UserEntity> userList = userService.queryList(query);
		PageUtils pageUtil = new PageUtils(userList, query.getTotle(), query.getLimit(), query.getPage());
		return ReturnMapInfo.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("user:user:info")
	public ReturnMapInfo info(@PathVariable("userId") Long userId){
		UserEntity user = userService.queryObject(userId);
		
		return ReturnMapInfo.ok().put("user", user);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("user:user:save")
	public ReturnMapInfo save(@RequestBody UserEntity user){
		userService.save(user);
		
		return ReturnMapInfo.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("user:user:update")
	public ReturnMapInfo update(@RequestBody UserEntity user){
		userService.update(user);
		
		return ReturnMapInfo.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("user:user:delete")
	public ReturnMapInfo delete(@RequestBody Long[] userIds){
		userService.deleteBatch(userIds);
		
		return ReturnMapInfo.ok();
	}
	
}
