package com.micro.boot.app.service.message.impl;

import com.micro.boot.app.dao.McAddressDao;
import com.micro.boot.app.dao.McMsgDao;
import com.micro.boot.app.dao.McUserDao;
import com.micro.boot.app.object.McAddress;
import com.micro.boot.app.object.McRegion;
import com.micro.boot.app.object.McRequestPage;
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
        request.setUserid(mcUserDao.getUserInfo(mobile).getId());
        mcMessageDao.messageAdd(request);
        McMsgRep response = mcMessageDao.getMessageById(request.getId());
        return response;
    }

}