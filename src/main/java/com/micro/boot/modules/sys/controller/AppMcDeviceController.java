package com.micro.boot.modules.sys.controller;

import com.micro.boot.common.annotation.SysLog;
import com.micro.boot.common.response.ReturnMapInfo;
import com.micro.boot.common.utils.Constant;
import com.micro.boot.common.utils.PageUtils;
import com.micro.boot.common.utils.Query;
import com.micro.boot.common.validator.ValidatorUtils;
import com.micro.boot.modules.sys.entity.AppMcDeviceEntity;
import com.micro.boot.modules.sys.service.AppMcDeviceService;
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
@RequestMapping("/device/device")
public class AppMcDeviceController extends AbstractController {
	@Autowired
	private AppMcDeviceService appMcDeviceService;

	
	/**
	 * 客户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("device:device:list")
	public ReturnMapInfo list(@RequestParam Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的设备列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		
		//查询列表数据
		Query query = new Query(params);
		List<AppMcDeviceEntity> list = appMcDeviceService.queryList(query);
		int total = appMcDeviceService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return ReturnMapInfo.ok().put("page", pageUtil);
	}
	
	/**
	 * 设备列表
	 */
	@RequestMapping("/select")
	@RequiresPermissions("device:device:select")
	public ReturnMapInfo select(){
		Map<String, Object> map = new HashMap<>();
		
		//如果不是超级管理员，则只查询自己所拥有的设备列表
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("createUserId", getUserId());
		}
		List<AppMcDeviceEntity> list = appMcDeviceService.queryList(map);
		
		return ReturnMapInfo.ok().put("list", list);
	}
	
	/**
	 * 设备信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("device:device:info")
	public ReturnMapInfo info(@PathVariable("id") Long id){
		AppMcDeviceEntity device = appMcDeviceService.queryObject(id);
		
		//查询设备对应的菜单
		return ReturnMapInfo.ok().put("device", device);
	}
	
	/**
	 * 保存设备
	 */
	@SysLog("保存设备")
	@RequestMapping("/save")
	@RequiresPermissions("device:device:save")
	public ReturnMapInfo save(@RequestBody AppMcDeviceEntity device){
		ValidatorUtils.validateEntity(device);
		appMcDeviceService.save(device);
		return ReturnMapInfo.ok();
	}
	
	/**
	 * 修改设备
	 */
	@SysLog("修改设备")
	@RequestMapping("/update")
	@RequiresPermissions("device:device:update")
	public ReturnMapInfo update(@RequestBody AppMcDeviceEntity device){
		appMcDeviceService.update(device);
		return ReturnMapInfo.ok();
	}
	
	/**
	 * 删除设备
	 */
	@SysLog("删除设备")
	@RequestMapping("/delete")
	@RequiresPermissions("device:device:delete")
	public ReturnMapInfo delete(@RequestBody Long[] currentIds){
		appMcDeviceService.deleteBatch(currentIds);
		return ReturnMapInfo.ok();
	}

}
