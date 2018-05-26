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
package com.micro.boot.app.service.queue;
/**
 * Created by 418206020 on 2018/4/18.
 */


import com.micro.boot.app.object.request.subscribe.McSubscribeReq;
import com.micro.boot.app.object.response.subscribe.McSubscribeRep;

import java.util.List;

/**
 *
 * @author Administrator
 * @create 2018/4/18
 * @since 1.0.0
 */
public interface McSubscriberService {


    void subscriber(String clientId, String[] topics);


    void unsubscribe();

    /**
     * 查询
     * @param request
     * @return
     */
    List<McSubscribeRep> listSubscriber(McSubscribeReq request);


}
