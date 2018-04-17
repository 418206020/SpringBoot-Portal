package com.micro.boot.app.service.device.impl;

import com.micro.boot.app.dao.McAddressDao;
import com.micro.boot.app.dao.McDeviceDao;
import com.micro.boot.app.dao.McUserDao;
import com.micro.boot.app.object.McAddress;
import com.micro.boot.app.object.McRegion;
import com.micro.boot.app.object.request.device.McDeviceReq;
import com.micro.boot.app.object.response.device.McDeviceRep;
import com.micro.boot.app.service.device.McDeviceService;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Constants;
import com.micro.boot.common.Message;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.utils.RedisUtils;
import org.springframework.http.HttpHeaders;
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

    @Override public McDeviceRep addDevice(HttpHeaders headers, McDeviceReq request) {
//        String language = headers.get(Constants.ACCEPT_LANGUAGE).get(0);
        String mobile = headers.get("mobile").get(0);
        request.setUserId(mcUserDao.getUserInfo(mobile).getId());
        if (null == request.getMcAddress()) {
            throw new RRException(AppCode.CODE_ERROR_INPUT, Message.MSG_EN_PARAMETERS_ERROR);
        }
        if (Constants.ZERO != mcDeviceDao.isDupMacId(request)) {
            throw new RRException(AppCode.CODE_ERROR_EXIST, Message.MSG_EN_PARAMETERS_EXIST);
        }
        mcAddressDao.addressAdd(request.getMcAddress());
        request.setAddressId(request.getMcAddress().getId());//设置关联地址
        mcDeviceDao.deviceAdd(request);
        McDeviceRep response = mcDeviceDao.getDeviceById(request.getId());
        //根据行政区划编码转换 当前禁用
//        request.setMcAddress(convert2Language(request.getMcAddress(), language));
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

    /**
     * 校验输入地址是否合法
     *
     * @param address
     *
     * @return
     */
    public boolean isValid(McAddress address) {
        //校验国家地区码是否一致
        if (address.getIsDefined() == Constants.ZERO) {
            //自定义
            if (null == address.getDefAddress()) {
                throw new RRException(AppCode.EXCETPTION_NULL_VALUE, Message.MSG_EN_NULL_VALUE);
            }
        } else {
            McRegion region = new McRegion();
            region.setRegionCode(address.getUndefProvince());//省
            if (null == mcAddressDao.getRegion(region)) {
                return false;
            }
            region.setRegionCode(address.getUndefCity());//市
            if (null == mcAddressDao.getRegion(region)) {
                return false;
            }
            region.setRegionCode(address.getUndefCounty());//县
            if (null == mcAddressDao.getRegion(region)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * @param address
     * @param language
     *
     * @return
     */
    public McAddress convert2Language(McAddress address, String language) {
        McAddress response = new McAddress();
        McRegion region = new McRegion();
        if (Constants.LANG_ZH_EN.equals(language)) {
            region.setRegionCode(address.getUndefProvince());//省
            response.setUndefProvince(mcAddressDao.getRegion(region).getRegionName());
            region.setRegionCode(address.getUndefCity());//市
            response.setUndefCity(mcAddressDao.getRegion(region).getRegionName());
            region.setRegionCode(address.getUndefCounty());//县
            response.setUndefCounty(mcAddressDao.getRegion(region).getRegionName());
        } else if (Constants.LANG_ZH_CN.equals(language) ||
                Constants.LANG_ZH_TW.equals(language))//TODO 暂不支持繁体
        {
            region.setRegionCode(address.getUndefProvince());//省
            McRegion obj = mcAddressDao.getRegion(region);
            response.setUndefProvince(obj.getRegionName());
            region.setRegionCode(address.getUndefCity());//市
            response.setUndefCity(mcAddressDao.getRegion(region).getRegionName());
            region.setRegionCode(address.getUndefCounty());//县
            response.setUndefCounty(mcAddressDao.getRegion(region).getRegionName());
        }
        return response;
    }
}