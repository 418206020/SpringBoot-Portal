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


    /**
     * 添加
     * @param request
     * @return
     */
    long deviceAdd(McDeviceReq request);

    /**
     *
     * @param id
     * @return
     */
    McDeviceRep getDeviceById(long id);

    /**
     *
     * @param macId
     * @return
     */
    McDeviceRep getDeviceByMacId(String macId);

    /**
     *
     * @param macId
     * @return
     */
    int deleteByMacId(String macId);

    /**
     * 根据macID更新
     * @param request
     * @return
     */
    int updateDeviceByMacId(McDeviceReq request);

    /**
     * 重复校验
     * @param request
     * @return
     */
    int isDupMacId(McDeviceReq request);

}