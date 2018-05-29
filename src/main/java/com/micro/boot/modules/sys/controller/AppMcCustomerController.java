package com.micro.boot.modules.sys.controller;

import com.micro.boot.app.service.user.McUserService;
import com.micro.boot.common.annotation.SysLog;
import com.micro.boot.common.response.ReturnMapInfo;
import com.micro.boot.common.utils.Constant;
import com.micro.boot.common.utils.PageUtils;
import com.micro.boot.common.utils.Query;
import com.micro.boot.common.validator.ValidatorUtils;
import com.micro.boot.modules.sys.entity.AppMcUserEntity;
import com.micro.boot.modules.sys.entity.SysRoleEntity;
import com.micro.boot.modules.sys.service.AppMcCustomerService;
import com.micro.boot.modules.sys.service.SysRoleMenuService;
import com.micro.boot.modules.sys.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户管理
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2016年11月8日 下午2:18:33
 */
@RestController
@RequestMapping("/customer/customer")
public class AppMcCustomerController extends AbstractController {
	@Autowired
	private AppMcCustomerService appMcCustomerService;

    @Autowired
    private McUserService mcUserService;
	
	/**
	 * 客户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("customer:customer:list")
	public ReturnMapInfo list(@RequestParam Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的客户列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		
		//查询列表数据
		Query query = new Query(params);
		List<AppMcUserEntity> list = appMcCustomerService.queryList(query);
		int total = appMcCustomerService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return ReturnMapInfo.ok().put("page", pageUtil);
	}
	
	/**
	 * 客户列表
	 */
	@RequestMapping("/select")
	@RequiresPermissions("customer:customer:select")
	public ReturnMapInfo select(){
		Map<String, Object> map = new HashMap<>();
		
		//如果不是超级管理员，则只查询自己所拥有的客户列表
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("createUserId", getUserId());
		}
		List<AppMcUserEntity> list = appMcCustomerService.queryList(map);
		
		return ReturnMapInfo.ok().put("list", list);
	}
	
	/**
	 * 客户信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("customer:customer:info")
	public ReturnMapInfo info(@PathVariable("id") Long id){
		AppMcUserEntity customer = appMcCustomerService.queryObject(id);
		
		//查询客户对应的菜单
		return ReturnMapInfo.ok().put("customer", customer);
	}
	
	/**
	 * 保存客户
	 */
	@SysLog("保存客户")
	@RequestMapping("/save")
	@RequiresPermissions("customer:customer:save")
	public ReturnMapInfo save(@RequestBody AppMcUserEntity customer){
		ValidatorUtils.validateEntity(customer);
		appMcCustomerService.save(customer);
		return ReturnMapInfo.ok();
	}
	
	/**
	 * 修改客户
	 */
	@SysLog("修改客户")
	@RequestMapping("/update")
	@RequiresPermissions("customer:customer:update")
	public ReturnMapInfo update(@RequestBody AppMcUserEntity customer){
		ValidatorUtils.validateEntity(customer);
		appMcCustomerService.update(customer);
		return ReturnMapInfo.ok();
	}
	
	/**
	 * 删除客户
	 */
	@SysLog("删除客户")
	@RequestMapping("/delete")
	@RequiresPermissions("customer:customer:delete")
	public ReturnMapInfo delete(@RequestBody Long[] currentIds){
		appMcCustomerService.deleteBatch(currentIds);
		return ReturnMapInfo.ok();
	}
}
