package com.micro.boot.app.service.device.impl;

import com.micro.boot.app.dao.McAddressDao;
import com.micro.boot.app.dao.McDeviceDao;
import com.micro.boot.app.dao.McUserDao;
import com.micro.boot.app.object.McAddress;
import com.micro.boot.app.object.request.device.McDeviceReq;
import com.micro.boot.app.object.request.user.McUserRegisterReq;
import com.micro.boot.app.object.response.device.McDeviceRep;
import com.micro.boot.app.object.response.user.McUserRegisterRep;
import com.micro.boot.app.service.device.McDeviceService;
import com.micro.boot.app.service.user.McRegisterService;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Constants;
import com.micro.boot.common.Message;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.utils.PwdTools;
import com.micro.boot.common.utils.RedisUtils;
import com.micro.boot.common.utils.Tools;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @create 2018/4/1
 * @since 1.0.0
 */
@Service
@Transactional
public class McDeviceServiceImpl implements McDeviceService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private McAddressDao mcAddressDao;

    @Resource
    private McDeviceDao mcDeviceDao;

    @Resource
    private McUserDao mcUserDao;

    @Override public McDeviceRep listDevice(McDeviceReq request) {
        return null;
    }

    @Override public void deleteDevice(McDeviceReq request) {

    }

    @Override public McDeviceRep editDevice(McDeviceReq request) {
        return null;
    }

    @Override public McDeviceRep getDetail(McDeviceReq request) {
        return null;
    }

    @Override public McDeviceRep addDevice(String mobile, McDeviceReq request) {
        request.setUserId(mcUserDao.getUserInfo(mobile).getId());
        if (null == request.getMcAddress()) {
            throw new RRException(AppCode.CODE_ERROR_INPUT, Message.MSG_EN_PARAMETERS_ERROR);
        }
        mcAddressDao.addressAdd(request.getMcAddress());
        request.setAddressId(request.getMcAddress().getId());//设置关联地址
        mcDeviceDao.deviceAdd(request);
        McDeviceRep response = mcDeviceDao.getDeviceById(request.getId());
        response.setMcAddress(request.getMcAddress());//设置返回地址
        return response;
    }

    /**
     * 校验设备属于该用户的操作权限
     *
     * @param mobile
     * @param request
     *
     * @return
     */
    @Override public boolean authDeviceByMobile(String mobile, McDeviceReq request) {
        return false;
    }

//    public McDeviceRep convert2Rep(McDeviceReq request){
//        McDeviceRep response = new McDeviceRep();
//        response.setCreateTime();
//        return response;
//    }
}