package com.micro.boot.app.controller.message;


import com.google.gson.Gson;
import com.micro.boot.app.annotation.MobileToken;
import com.micro.boot.app.object.McRequestPage;
import com.micro.boot.app.object.request.msg.McBatchMsgReq;
import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.app.object.response.msg.McMsgRep;
import com.micro.boot.app.service.message.McMessageService;
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
import java.util.List;

/**
 * 消息
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2018-03-23 15:31
 */
@RestController
@RequestMapping(Constants.APP + Constants.SEPPARATOR_SLASH + ModuleConstant.MODULE_MSG)
public class McMessageController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private McMessageService mcMessageService;

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
    @ApiOperation(value = "添加消息", notes = "添加消息", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.CODE_ERROR_INPUT, message = Message.MSG_EN_PARAMETERS_ERROR),
            @ApiResponse(code = AppCode.CODE_ERROR_EXIST, message = Message.MSG_EN_PARAMETERS_EXIST),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @MobileToken
    @PostMapping(AppRestUrl.MC_MSG_ADD)
    public ReturnAppInfo<McMsgRep> add(@RequestBody BodyInfo bodyInfo,
                                       @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.MC_MSG_ADD + ",Param:", headers);
        McMsgReq request = new Gson().fromJson(bodyInfo.decryptData(), McMsgReq.class);
        McMsgRep response = mcMessageService.addMessage(headers, request);
        return ReturnAppInfo.successEncrypt(response);
    }

    /**
     * 查询详情：id或者msgId
     *
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "查询消息详情", notes = "查询消息详情", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @MobileToken
    @GetMapping(AppRestUrl.MC_MSG_GET)
    public ReturnAppInfo<McMsgRep> getOne(@PathVariable String msgId,
                                          @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.MC_MSG_GET + ",Param:", headers);
        McMsgRep response = mcMessageService.getMessageById(Long.parseLong(msgId));
        return ReturnAppInfo.successEncrypt(response);
    }

    /**
     * 修改消息
     *
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "修改消息信息", notes = "修改消息信息", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @MobileToken
    @PostMapping(AppRestUrl.MC_MSG_EDIT)
    public ReturnAppInfo<McMsgRep> edit(@RequestBody BodyInfo bodyInfo,
                                        @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.MC_MSG_EDIT + ",Param:", headers);
        McMsgReq request = new Gson().fromJson(bodyInfo.decryptData(), McMsgReq.class);
        McMsgRep response = mcMessageService.editMessage(request);
        return ReturnAppInfo.successEncrypt(response);
    }

    /**
     * 删除消息
     *
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "删除消息", notes = "删除消息", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @MobileToken
    @DeleteMapping(AppRestUrl.MC_MSG_DEL)
    public ReturnAppInfo<McMsgRep> delete(@PathVariable String msgId,
                                          @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.MC_MSG_DEL + ",Param:", headers);
        mcMessageService.deleteMessage(Long.parseLong(msgId));
        return ReturnAppInfo.successEncrypt(null);
    }

    /**
     * 查询消息列表-指定设备
     *
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "查询指定设备消息列表", notes = "查询指定设备消息列表", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @MobileToken
    @GetMapping(AppRestUrl.MC_MSG_LIST_USER_DEVICE)
    public ReturnAppInfo<McMsgRep> listByUser(@PathVariable String devId,
                                              @RequestParam Integer pageNo,
                                              @RequestParam Integer pageSize,
                                              @RequestParam(required = false) String orderBy,
                                              @RequestParam(required = false) String orderDesc,
                                              @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.MC_MSG_LIST_USER_DEVICE + ",Param:", headers);
        McRequestPage page = new McRequestPage();
        page.setOrderDesc(orderDesc);
        page.setOrderBy(orderBy);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        McBatchMsgReq req = new McBatchMsgReq();
        List<McMsgRep> response = mcMessageService.listMessageByUserDevId(headers, req, page, Long.parseLong(devId));
        return ReturnAppInfo.successEncrypt(response);
    }

    /**
     * 查询消息列表
     *
     * @param headers
     *
     * @return
     *
     * @throws Exception
     */
    @ApiOperation(value = "查询消息列表", notes = "查询消息列表", response = ReturnAppInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = AppCode.SUCCESS_RESPONSE, message = Message.MSG_OK_200),
            @ApiResponse(code = AppCode.ERROR_CODE_404, message = Message.MSG_EN_ERROR_404),
            @ApiResponse(code = AppCode.EXCETPTION_FAIL, message = Message.MSG_EN_ERROR_500)}
    )
    @MobileToken
    @GetMapping(AppRestUrl.MC_MSG_LIST_USER)
    public ReturnAppInfo<McMsgRep> listByUserDev(
            @RequestParam Integer pageNo,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String orderDesc,
            @RequestHeader HttpHeaders headers) throws Exception
    {
        logger.info(AppRestUrl.MC_MSG_LIST_USER + ",Param:", headers);
        McRequestPage page = new McRequestPage();
        page.setOrderDesc(orderDesc);
        page.setOrderBy(orderBy);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        McBatchMsgReq req = new McBatchMsgReq();
        List<McMsgRep> response = mcMessageService.listMessageByUser(headers, req, page);
        return ReturnAppInfo.successEncrypt(response);
    }


}