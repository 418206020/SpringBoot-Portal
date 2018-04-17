/**
 * Copyright (C), 2018
 * FileName: McRegisterService
 * Author:   Administrator
 * Date:     2018/4/1 16:03
 * Description: 注册
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.micro.boot.app.service.device;
/**
 * Created by 418206020 on 2018/4/18.
 */


import com.micro.boot.app.object.McAddress;
import com.micro.boot.app.object.request.device.McDeviceReq;
import com.micro.boot.app.object.request.user.McPasswordResetReq;
import com.micro.boot.app.object.request.user.McUserInfoReq;
import com.micro.boot.app.object.request.user.McUserLoginReq;
import com.micro.boot.app.object.response.device.McDeviceRep;
import com.micro.boot.app.object.response.user.McUserInfoRep;
import com.micro.boot.app.object.response.user.McUserLoginRep;
import org.springframework.http.HttpHeaders;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @create 2018/4/18
 * @since 1.0.0
 */
public interface McDeviceService {


    /**
     * @param request
     *
     * @return
     */
    McDeviceRep listDevice(McDeviceReq request);

    /**
     * @param request
     */
    void deleteDevice(McDeviceReq request);

    /**
     * @param request
     *
     * @return
     */
    McDeviceRep editDevice(McDeviceReq request);

    /**
     * @param macId
     *
     * @return
     */
    McDeviceRep getDetail(String macId);

    /**
     * @param request
     *
     * @return
     */
    McDeviceRep addDevice(HttpHeaders headers, McDeviceReq request);

    /**
     * 校验设备属于该用户的操作权限
     * @param mobile
     * @param request
     * @return
     */
    boolean authDeviceByMobile(String mobile, McDeviceReq request);

    McAddress convert2Language(McAddress address, String language);

    boolean isValid(McAddress address);

}
