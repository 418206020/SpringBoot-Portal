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
package com.micro.boot.app.service.message;
/**
 * Created by 418206020 on 2018/4/18.
 */


import com.micro.boot.app.object.McAddress;
import com.micro.boot.app.object.McRequestPage;
import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.app.object.response.msg.McMsgRep;
import org.springframework.http.HttpHeaders;

import java.util.List;

/**
 * @author Administrator
 * @create 2018/4/18
 * @since 1.0.0
 */
public interface McMessageService {


    /**
     * @param page
     *
     * @return
     */
    List<McMsgRep> listMessage(HttpHeaders headers, McRequestPage page, String devType, Integer devStatus);

    /**
     * @param msgId
     */
    void deleteMessage(String msgId);

    /**
     * @param request
     *
     * @return
     */
    McMsgRep editMessage(McMsgReq request);

    /**
     * @param msgId
     *
     * @return
     */
    McMsgRep getDetail(String msgId);

    /**
     * @param request
     *
     * @return
     */
    McMsgRep addMessage(HttpHeaders headers, McMsgReq request);


}
