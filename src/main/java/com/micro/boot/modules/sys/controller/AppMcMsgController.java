package com.micro.boot.modules.sys.controller;

import com.micro.boot.common.annotation.SysLog;
import com.micro.boot.common.response.ReturnMapInfo;
import com.micro.boot.common.utils.Constant;
import com.micro.boot.common.utils.PageUtils;
import com.micro.boot.common.utils.Query;
import com.micro.boot.common.validator.ValidatorUtils;
import com.micro.boot.modules.sys.entity.AppMcMsgEntity;
import com.micro.boot.modules.sys.service.AppMcMsgService;
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
@RequestMapping("/msg/msg")
public class AppMcMsgController extends AbstractController {
	@Autowired
	private AppMcMsgService appMcMsgService;

	
	/**
	 * 客户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("msg:msg:list")
	public ReturnMapInfo list(@RequestParam Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的消息列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		
		//查询列表数据
		Query query = new Query(params);
		List<AppMcMsgEntity> list = appMcMsgService.queryList(query);
		int total = appMcMsgService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return ReturnMapInfo.ok().put("page", pageUtil);
	}
	
	/**
	 * 消息列表
	 */
	@RequestMapping("/select")
	@RequiresPermissions("msg:msg:select")
	public ReturnMapInfo select(){
		Map<String, Object> map = new HashMap<>();
		
		//如果不是超级管理员，则只查询自己所拥有的消息列表
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("createUserId", getUserId());
		}
		List<AppMcMsgEntity> list = appMcMsgService.queryList(map);
		
		return ReturnMapInfo.ok().put("list", list);
	}
	
	/**
	 * 消息信息
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("msg:msg:info")
	public ReturnMapInfo info(@PathVariable("roleId") Long roleId){
		AppMcMsgEntity role = appMcMsgService.queryObject(roleId);
		
		//查询消息对应的菜单
		return ReturnMapInfo.ok().put("role", role);
	}
	
	/**
	 * 保存消息
	 */
	@SysLog("保存消息")
	@RequestMapping("/save")
	@RequiresPermissions("msg:msg:save")
	public ReturnMapInfo save(@RequestBody AppMcMsgEntity role){
		ValidatorUtils.validateEntity(role);
		
//		role.setCreateMsgId(getMsgId());
		appMcMsgService.save(role);
		
		return ReturnMapInfo.ok();
	}
	
	/**
	 * 修改消息
	 */
	@SysLog("修改消息")
	@RequestMapping("/update")
	@RequiresPermissions("msg:msg:update")
	public ReturnMapInfo update(@RequestBody AppMcMsgEntity role){
		ValidatorUtils.validateEntity(role);
		
//		role.setCreateMsgId(getMsgId());
		appMcMsgService.update(role);
		
		return ReturnMapInfo.ok();
	}
	
	/**
	 * 删除消息
	 */
	@SysLog("删除消息")
	@RequestMapping("/delete")
	@RequiresPermissions("msg:msg:delete")
	public ReturnMapInfo delete(@RequestBody Long[] roleIds){
		appMcMsgService.deleteBatch(roleIds);
		
		return ReturnMapInfo.ok();
	}
}
