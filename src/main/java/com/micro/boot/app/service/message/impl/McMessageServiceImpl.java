package com.micro.boot.app.service.message.impl;

import com.micro.boot.app.dao.McAddressDao;
import com.micro.boot.app.dao.McMsgDao;
import com.micro.boot.app.dao.McUserDao;
import com.micro.boot.app.object.McAddress;
import com.micro.boot.app.object.McRegion;
import com.micro.boot.app.object.McRequestPage;
import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.app.object.response.msg.McMsgRep;
import com.micro.boot.app.service.message.McMessageService;
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
    public List<McMsgRep> listMessage(HttpHeaders headers, McRequestPage page, String devType, Integer devStatus)
    {
        //构造查询条件

        return null;
    }

    /**
     * 删除
     *
     * @param msgId
     */
    @Override
    public void deleteMessage(String msgId) {
        mcMessageDao.deleteById(msgId);
    }

    @Override
    public McMsgRep editMessage(McMsgReq request) {
        return mcMessageDao.updateMsgById(request);
    }

    /**
     * 查询
     *
     * @param msgId
     *
     * @return
     */
    @Override
    public McMsgRep getDetail(String msgId) {
        if (!StringUtils.isEmpty(msgId)) {
            McMsgRep response = mcMessageDao.getMsgById(msgId);
            return response;
        } else {
            throw new RRException(AppCode.CODE_ERROR_INPUT, Message.MSG_EN_PARAMETERS_ERROR);
        }
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
//        String language = headers.get(Constants.ACCEPT_LANGUAGE).get(0);
        String mobile = headers.get("mobile").get(0);
        request.setUserid(mcUserDao.getUserInfo(mobile).getId());
        McMsgRep response = mcMessageDao.getMessageById(request.getId());
        return response;
    }

}