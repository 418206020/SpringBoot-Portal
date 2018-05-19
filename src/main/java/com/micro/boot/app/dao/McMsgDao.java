package com.micro.boot.app.dao;

import com.micro.boot.app.object.request.msg.McBatchMsgReq;
import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.app.object.response.msg.McMsgRep;
import com.micro.boot.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-10-23 21:23:54
 */
@Mapper
public interface McMsgDao extends BaseDao<McMsgReq> {

    /**
     * 添加
     *
     * @param request
     *
     * @return
     */
    long messageAdd(McMsgReq request);

    /**
     *
     * @param id
     * @return
     */
    McMsgRep getMessageById(Long id);

    /**
     *
     * @param request
     * @return
     */
    int updateMsgById(McMsgReq request);

    /**
     *
     * @param id
     * @return
     */
    int deleteMsgById(Long id);

    /**
     * 批量查询
     * @param requestPage
     * @return
     */
    List<McMsgRep> listMsgByUser(McBatchMsgReq requestPage);

}