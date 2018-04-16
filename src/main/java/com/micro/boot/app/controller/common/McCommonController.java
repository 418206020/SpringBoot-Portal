package com.micro.boot.app.controller.common;


import com.google.gson.Gson;
import com.micro.boot.app.object.request.device.McDeviceReq;
import com.micro.boot.app.object.response.device.McDeviceRep;
import com.micro.boot.app.object.response.user.McUserLoginRep;
import com.micro.boot.app.service.user.McRegisterService;
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
public class McCommonController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private McRegisterService mcRegisterService;

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
//    @ApiOperation(value = "添加设备", notes = "添加设备", response = ReturnAppInfo.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
//            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
//            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
//    )
//    @PostMapping(AppRestUrl.XXXXXX)
//    public ReturnAppInfo<McUserLoginRep> add(@RequestBody BodyInfo bodyInfo,
//                                                  @RequestHeader HttpHeaders headers) throws Exception
//    {
//        logger.info(AppRestUrl.XXXXXX+",Param:", mobile);
//        McDeviceReq request = new Gson().fromJson(bodyInfo.decryptData(), XXXXXXXXXXXXX.class);
//        McDeviceRep response = new XXXXXXXXXX();
//        return ReturnAppInfo.successEncrypt(response);
//    }


}