package com.micro.boot.app.dao;

import com.micro.boot.app.object.request.subscribe.McSubscribeReq;
import com.micro.boot.app.object.response.subscribe.McSubscribeRep;
import com.micro.boot.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017-10-23 21:23:54
 */
@Mapper
public interface McSubscribeDao extends BaseDao<McSubscribeReq> {


    /**
     * 添加
     *
     * @param request
     *
     * @return
     */
    long addSubscriber(McSubscribeReq request);

    /**
     * @param request
     *
     * @return
     */
    int updateSubscriberById(McSubscribeReq request);

    /**
     * 取消所有订阅
     * @return
     */
    int unsubscribe();


    List<McSubscribeRep> listSubsriber(McSubscribeReq request);
}