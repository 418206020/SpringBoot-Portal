package com.micro.boot.app.controller.device;


import com.google.gson.Gson;
import com.micro.boot.app.object.request.device.McDeviceReq;
import com.micro.boot.app.object.response.device.McDeviceRep;
import com.micro.boot.app.object.response.user.McUserLoginRep;
import com.micro.boot.app.service.device.McDeviceService;
import com.micro.boot.common.*;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.response.ReturnAppInfo;
import com.micro.boot.common.utils.RedisUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 设备
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2018-03-23 15:31
 */
@RestController
@RequestMapping(Constants.APP + Constants.SEPPARATOR_SLASH + ModuleConstant.MODULE_DEVICE)
public class McDeviceController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private McDeviceService mcDeviceService;

    @Resource
    private RedisUtils redisUtils;


    /**
     * 添加
     *
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "添加设备", notes = "添加设备", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.CODE_ERROR_INPUT, message = Message.MSG_EN_PARAMETERS_ERROR),
            @ApiResponse(code = AppCode.CODE_ERROR_EXIST, message = Message.MSG_EN_PARAMETERS_EXIST),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @PostMapping(AppRestUrl.MC_DEVICE_ADD)
    public ReturnAppInfo<McUserLoginRep> add(@RequestBody BodyInfo bodyInfo,
                                                  @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.MC_DEVICE_ADD+",Param:", headers);
        McDeviceReq request = new Gson().fromJson(bodyInfo.decryptData(), McDeviceReq.class);
        McDeviceRep response = mcDeviceService.addDevice(headers,request);
        return ReturnAppInfo.successEncrypt(response);
    }

    /**
     * 查询详情
     *
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "查询设备详情", notes = "查询设备详情", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @GetMapping(AppRestUrl.MC_DEVICE_GET)
    public ReturnAppInfo<McUserLoginRep> getOne(@RequestBody BodyInfo bodyInfo,
                                             @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.MC_DEVICE_GET+",Param:", headers);
        McDeviceReq request = new Gson().fromJson(bodyInfo.decryptData(), McDeviceReq.class);
        McDeviceRep response = mcDeviceService.getDetail(request);
        return ReturnAppInfo.successEncrypt(response);
    }

    /**
     * 修改设备
     *
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "修改设备信息", notes = "修改设备信息", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @PostMapping(AppRestUrl.MC_DEVICE_EDIT)
    public ReturnAppInfo<McUserLoginRep> edit(@RequestBody BodyInfo bodyInfo,
                                                      @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.MC_DEVICE_EDIT+",Param:", headers);
        McDeviceReq request = new Gson().fromJson(bodyInfo.decryptData(), McDeviceReq.class);
        McDeviceRep response = mcDeviceService.editDevice(request);
        return ReturnAppInfo.successEncrypt(response);
    }

    /**
     * 删除设备
     *
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "删除设备", notes = "删除设备", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @DeleteMapping(AppRestUrl.MC_DEVICE_DEL)
    public ReturnAppInfo<McUserLoginRep> delete(@RequestBody BodyInfo bodyInfo,
                                                    @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.MC_DEVICE_DEL+",Param:", headers);
        McDeviceReq request = new Gson().fromJson(bodyInfo.decryptData(), McDeviceReq.class);
        mcDeviceService.deleteDevice(request);
        return ReturnAppInfo.successEncrypt(null);
    }

    /**
     * 查询设备列表
     *
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "查询设备列表", notes = "查询设备列表", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @GetMapping(AppRestUrl.MC_DEVICE_LIST)
    public ReturnAppInfo<McUserLoginRep> list(@RequestBody BodyInfo bodyInfo,
                                                @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.MC_DEVICE_LIST+",Param:", headers);
        McDeviceReq request = new Gson().fromJson(bodyInfo.decryptData(), McDeviceReq.class);
        McDeviceRep response =  mcDeviceService.listDevice(request);
        return ReturnAppInfo.successEncrypt(response);
    }

}