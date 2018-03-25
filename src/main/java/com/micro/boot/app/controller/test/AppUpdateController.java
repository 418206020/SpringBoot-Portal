package com.micro.boot.app.controller.test;

import com.google.gson.Gson;
import com.micro.boot.common.Constants;
import com.micro.boot.common.response.ReturnAppInfo;
import com.micro.boot.common.utils.PageUtils;
import com.micro.boot.common.utils.Query;
import com.micro.boot.app.service.test.AppUpdateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


/**
 * APP版本管理
 * 
 * @author huliang
 * @email 418206020@qq.com
 * @date 2018-01-05 15:28:57
 */
@Api(value = "API - AppUpdateController ", description = "APP版本管理")
@RestController
@RequestMapping(Constants.APP)
public class AppUpdateController {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "appUpdateService")
	private AppUpdateService appUpdateService;
	
	/**
	 * 列表
	 */
    @ApiOperation(value="列表", notes="列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true,dataType = "string", paramType = "query", defaultValue = "")})
	@PostMapping("/appUpdate/list")
	public ReturnAppInfo list(@RequestBody ReturnAppInfo returnAppInfo)throws Exception{
        logger.info("AppUpdateController 列表", returnAppInfo.decryptData());
        HashMap<String,Object> params = new Gson().fromJson(returnAppInfo.decryptData(),HashMap.class);
		//查询列表数据
        Query query = new Query(params);
        query.isPaging(true);
		List<HashMap<String,Object>> appUpdateList = appUpdateService.queryList(query);
		PageUtils pageUtil = new PageUtils(appUpdateList, query.getTotle(), query.getLimit(), query.getPage());
        return ReturnAppInfo.success().setEncryptData(pageUtil);
	}
	
	
	/**
	 * 信息
	 */
    @ApiOperation(value="信息", notes="信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true,dataType = "string", paramType = "query", defaultValue = "")})
	@PostMapping("/appUpdate/info")
	public ReturnAppInfo info(@RequestBody ReturnAppInfo returnAppInfo)throws Exception{
        logger.info("AppUpdateController 信息", returnAppInfo.decryptData());
        HashMap<String,Object> params = new Gson().fromJson(returnAppInfo.decryptData(),HashMap.class);
        HashMap<String,Object> data = appUpdateService.queryObject(params);
        return ReturnAppInfo.success().setEncryptData(data);
	}
	
	/**
	 * 保存
	 */
    @ApiOperation(value="保存", notes="保存")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true,dataType = "string", paramType = "query", defaultValue = "")})
	@PostMapping("/appUpdate/save")
	public ReturnAppInfo save(@RequestBody ReturnAppInfo returnAppInfo)throws Exception{
        logger.info("AppUpdateController 保存", returnAppInfo.decryptData());
        HashMap<String,Object> params = new Gson().fromJson(returnAppInfo.decryptData(),HashMap.class);
		appUpdateService.saveInfo(params);
        return ReturnAppInfo.success();
	}
	
	/**
	 * 修改
	 */
    @ApiOperation(value="修改", notes="修改")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true,dataType = "string", paramType = "query", defaultValue = "")})
	@PostMapping("/appUpdate/update")
	public ReturnAppInfo update(@RequestBody ReturnAppInfo returnAppInfo)throws Exception{
        logger.info("AppUpdateController 修改", returnAppInfo.decryptData());
        HashMap<String,Object> params = new Gson().fromJson(returnAppInfo.decryptData(),HashMap.class);
		appUpdateService.updateInfo(params);
        return ReturnAppInfo.success();
	}
	
	/**
	 * 删除
	 */
    @ApiOperation(value="删除", notes="删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token", required = true,dataType = "string", paramType = "query", defaultValue = "")})
	@PostMapping("/appUpdate/delete")
	public ReturnAppInfo delete(@RequestBody ReturnAppInfo returnAppInfo)throws Exception{
        logger.info("AppUpdateController 修改", returnAppInfo.decryptData());
        HashMap<String,Object> params = new Gson().fromJson(returnAppInfo.decryptData(),HashMap.class);
		appUpdateService.deleteInfo(params);
        return ReturnAppInfo.success();
	}
	
}
