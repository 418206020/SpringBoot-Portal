package com.micro.boot.modules.sys.controller;

import com.micro.boot.common.annotation.SysLog;
import com.micro.boot.common.response.ReturnMapInfo;
import com.micro.boot.common.utils.Constant;
import com.micro.boot.common.utils.PageUtils;
import com.micro.boot.common.utils.Query;
import com.micro.boot.common.validator.ValidatorUtils;
import com.micro.boot.modules.sys.entity.AppMcSubscribeEntity;
import com.micro.boot.modules.sys.service.AppMcSubscribeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2016年11月8日 下午2:18:33
 */
@RestController
@RequestMapping("/subscribe/subscribe")
public class AppMcSubscribeController extends AbstractController {
	@Autowired
	private AppMcSubscribeService appMcSubscribeService;

	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("subscribe:subscribe:list")
	public ReturnMapInfo list(@RequestParam Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的订阅列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		
		//查询列表数据
		Query query = new Query(params);
		List<AppMcSubscribeEntity> list = appMcSubscribeService.queryList(query);
		int total = appMcSubscribeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return ReturnMapInfo.ok().put("page", pageUtil);
	}
	
	/**
	 * 订阅列表
	 */
	@RequestMapping("/select")
	@RequiresPermissions("subscribe:subscribe:select")
	public ReturnMapInfo select(){
		Map<String, Object> map = new HashMap<>();
		
		//如果不是超级管理员，则只查询自己所拥有的订阅列表
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("createUserId", getUserId());
		}
		List<AppMcSubscribeEntity> list = appMcSubscribeService.queryList(map);
		
		return ReturnMapInfo.ok().put("list", list);
	}
	
	/**
	 * 订阅信息
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("subscribe:subscribe:info")
	public ReturnMapInfo info(@PathVariable("roleId") Long roleId){
		AppMcSubscribeEntity role = appMcSubscribeService.queryObject(roleId);
		
		//查询订阅对应的菜单
		return ReturnMapInfo.ok().put("role", role);
	}
	
	/**
	 * 保存订阅
	 */
	@SysLog("保存订阅")
	@RequestMapping("/save")
	@RequiresPermissions("subscribe:subscribe:save")
	public ReturnMapInfo save(@RequestBody AppMcSubscribeEntity role){
		ValidatorUtils.validateEntity(role);
		
//		role.setCreateSubscribeId(getSubscribeId());
		appMcSubscribeService.save(role);
		
		return ReturnMapInfo.ok();
	}
	
	/**
	 * 修改订阅
	 */
	@SysLog("修改订阅")
	@RequestMapping("/update")
	@RequiresPermissions("subscribe:subscribe:update")
	public ReturnMapInfo update(@RequestBody AppMcSubscribeEntity role){
		ValidatorUtils.validateEntity(role);
		
//		role.setCreateSubscribeId(getSubscribeId());
		appMcSubscribeService.update(role);
		
		return ReturnMapInfo.ok();
	}
	
	/**
	 * 删除订阅
	 */
	@SysLog("删除订阅")
	@RequestMapping("/delete")
	@RequiresPermissions("subscribe:subscribe:delete")
	public ReturnMapInfo delete(@RequestBody Long[] roleIds){
		appMcSubscribeService.deleteBatch(roleIds);
		
		return ReturnMapInfo.ok();
	}
}
