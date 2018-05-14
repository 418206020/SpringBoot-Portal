package com.micro.boot.app.dao;

import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.app.object.response.msg.McMsgRep;
import com.micro.boot.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-10-23 21:23:54
 */
@Mapper
public interface McMsgDao extends BaseDao<McMsgReq> {


    /**
     *
     * @param id
     * @return
     */
    McMsgRep getMessageById(Long id);

    McMsgRep getMsgById(String msgId);

    McMsgRep updateMsgById(McMsgReq request);

    int deleteById(String msgId);
}