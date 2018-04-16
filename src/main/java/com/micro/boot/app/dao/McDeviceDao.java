package com.micro.boot.app.dao;

import com.micro.boot.app.object.request.device.McDeviceReq;
import com.micro.boot.app.object.response.device.McDeviceRep;
import com.micro.boot.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-10-23 21:23:54
 */
@Mapper
public interface McDeviceDao extends BaseDao<McDeviceReq> {


    long deviceAdd(McDeviceReq request);

    McDeviceRep getDeviceById(long id);

    int isDupMacId(McDeviceReq request);

}