package com.micro.boot.app.service.message.impl;

import com.micro.boot.app.dao.*;
import com.micro.boot.app.object.McAddress;
import com.micro.boot.app.object.McRegion;
import com.micro.boot.app.object.McRequestPage;
import com.micro.boot.app.object.request.device.McDeviceReq;
import com.micro.boot.app.object.request.msg.McBatchMsgReq;
import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.app.object.response.msg.McMsgRep;
import com.micro.boot.app.service.message.McMessageService;
import com.micro.boot.app.utils.AppUtils;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Constants;
import com.micro.boot.common.Message;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.thirdparty.paho.AnalyticUtil;
import com.micro.boot.thirdparty.paho.DeviceStateRep;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
@Service
@Transactional
public class McMessageServiceImpl implements McMessageService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private McAddressDao mcAddressDao;

    @Resource
    private McMsgDao mcMessageDao;

    @Resource
    private McTopicDao mcTopicDao;
    @Resource
    private McDeviceDao mcDeviceDao;

    @Resource
    private McUserDao mcUserDao;

    /**
     * 批量分页查询
     *
     * @param headers
     * @param page
     *
     * @return
     */
    @Override
    public List<McMsgRep> listMessageByUserDevId(HttpHeaders headers, McBatchMsgReq req, McRequestPage page, long devId)
    {
        //构造查询条件
        McBatchMsgReq request = new McBatchMsgReq();
        request.setPage(page.getPageNo(), page.getPageSize());
        request.setOrderString(AppUtils.getOrderString(page));
        String mobile = headers.get("mobile").get(Constants.ZERO);
        request.setUserid(mcUserDao.getUserInfo(mobile).getId());//设置查询的用户
        //其他查询条件:目前暂不支持其他条件
        if (StringUtils.equals("0", String.valueOf(devId))) {
            request.setDevid(null);
        } else {
            request.setDevid(String.valueOf(devId));
        }
        List<McMsgRep> deviceRepList = mcMessageDao.listMsgByUser(request);
        return deviceRepList;
    }

    /**
     * 批量分页查询
     *
     * @param headers
     * @param page
     *
     * @return
     */
    @Override
    public List<McMsgRep> listMessageByUser(HttpHeaders headers, McBatchMsgReq req, McRequestPage page)
    {
        //不限制dev 传0
        return listMessageByUserDevId(headers, req, page, Constants.ZERO);
    }

    /**
     * 删除
     *
     * @param msgId
     */
    @Override
    public void deleteMessage(long msgId) {
        mcMessageDao.deleteMsgById(msgId);
    }

    @Override
    public McMsgRep editMessage(McMsgReq request) {
        mcMessageDao.updateMsgById(request);
        return mcMessageDao.getMessageById(request.getId());
    }

    /**
     * 查询
     *
     * @param msgId
     *
     * @return
     */
    @Override
    public McMsgRep getMessageById(long msgId) {
        McMsgRep response = mcMessageDao.getMessageById(msgId);
        return response;
    }

    /**
     * 添加
     *
     * @param headers
     * @param request
     *
     * @return
     */
    @Override
    public McMsgRep addMessage(HttpHeaders headers, McMsgReq request) {
        String mobile = headers.get("mobile").get(0);
        saveMessage(mobile, request);
        McMsgRep response = mcMessageDao.getMessageById(request.getId());
        return response;
    }

    /**
     * @param request
     */
    @Override
    public void saveMessage(String mobile, McMsgReq request) {
        request.setUserid(mcUserDao.getUserInfo(mobile).getId());
        mcMessageDao.messageAdd(request);
    }

    /**
     * 实时更新
     *
     * @param message
     */
    @Override
    public void saveMQTT(String client, String topic, String message) {
        McMsgReq request = new McMsgReq();
        String[] topicName = topic.split(Constants.SEPPARATOR_SLASH);
        if (topicName.length > Constants.ONE) {
            request.setTopicName(topicName[Constants.ONE]);
            request.setMsgType(String.valueOf(Constants.ZERO));//类别
        } else {
            request.setTopicName(topic);
            request.setMsgType(String.valueOf(Constants.ONE));//类别
        }
        request.setTimeConsumer(new Date());
        request.setTimeProducer(new Date());
        request.setMsgType(client);
        request.setMessage(message);
        mcMessageDao.messageAdd(request);

        //更新所属用户
        mcTopicDao.updateUserByMsg(String.valueOf(request.getId()));
        //更新所属设备
        mcTopicDao.updateDeviceByMsg(String.valueOf(request.getId()));
        //解析消息，更新告警状态F
        McDeviceReq devide = new McDeviceReq();
        DeviceStateRep rep = AnalyticUtil.analytic(message);
        //接收主题作为macId
        devide.setDevMacid(request.getTopicName());
        devide.setElectricity(String.valueOf(rep.getElectricQuantity1()));
        if(rep.isAlarm()){
            devide.setDevStatus(0);
        }else {
            devide.setDevStatus(1);
        }
        if(rep.getWifiStatus().equals("STA_GRTIP")){
            devide.setStatusWifi(0);
        }else {
            devide.setStatusWifi(1);
        }
        devide.setPublicExtendParam1(rep.getIP());
        devide.setPublicExtendParam2(rep.getMac());
        devide.setPublicExtendParam3(String.valueOf(rep.getBluetoothRSSI()));

        mcDeviceDao.updateDeviceByMacId(devide);
    }

}