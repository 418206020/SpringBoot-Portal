package com.micro.boot.app.dao;

import com.micro.boot.app.object.McAddress;
import com.micro.boot.app.object.McRegion;
import com.micro.boot.app.object.request.user.McUserInfoReq;
import com.micro.boot.app.object.request.user.McUserRegisterReq;
import com.micro.boot.app.object.response.device.McDeviceRep;
import com.micro.boot.app.object.response.user.McUserInfoRep;
import com.micro.boot.app.object.response.user.McUserLoginRep;
import com.micro.boot.app.object.response.user.McUserRegisterRep;
import com.micro.boot.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-10-23 21:23:54
 */
@Mapper
public interface McAddressDao extends BaseDao<McAddress> {

    long addressAdd(McAddress request);

    McAddress getAddressById(long id);

    /**
     * 可查的四个字段：regionCode、regionShortnameEn、fid、level
     * @param region
     * @return
     */
    McRegion getRegion(McRegion region);


}