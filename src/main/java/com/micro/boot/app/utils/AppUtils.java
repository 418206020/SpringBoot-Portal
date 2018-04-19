package com.micro.boot.app.utils;

import com.micro.boot.app.object.McRequestPage;
import com.micro.boot.common.AppCode;
import com.micro.boot.common.Constants;
import com.micro.boot.common.Message;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.utils.MD5;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * App工具类
 *
 * @author huliang
 * @email 418206020@qq.com
 * @date 2017/9/21 22:21
 */
@Component
public class AppUtils {

    /**
     * 校验参数
     *
     * @param page
     *
     * @return
     */
    public static boolean validateRequestPage(McRequestPage page) {
        if (null == page.getPageNo() || null == page.getPageSize()) {
            return false;
        }
        if (null == page.getOrderBy() || null == page.getOrderDesc()) {
            return true;
        } else if (page.getOrderDesc() != null && page.getOrderBy() != null) {
            if (page.getOrderBy().split(",").length == page.getOrderDesc().split(",").length) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 获取postman排序
     * @param page
     * @return
     */
    public static String getOrderString(McRequestPage page) {
        String ret = "";
        if (null == page.getPageNo() || null == page.getPageSize()) {
//            logger.error("page's no or size cannot be null.");
            throw new RRException(AppCode.EXCETPTION_FAIL, Message.MSG_EN_ERROR_500);
        }
        if (null == page.getOrderBy() || null == page.getOrderDesc()) {
            return ret;
        } else if (page.getOrderDesc() != null && page.getOrderBy() != null) {
            String[] head = page.getOrderBy().split(",");
            String[] tail = page.getOrderDesc().split(",");

            if (head.length == tail.length) {
                for (int k = 0; k < head.length; k++) {
                    if (k == head.length - 1) {
                        ret = ret.concat(head[k]).concat(" ").concat(tail[k]);
                    } else {
                        ret = ret.concat(head[k]).concat(" ").concat(tail[k]).concat(", ");
                    }
                }
                return ret;
            } else {
                throw new RRException(AppCode.EXCETPTION_FAIL, Message.MSG_EN_ERROR_500);
            }
        } else {
            throw new RRException(AppCode.EXCETPTION_FAIL, Message.MSG_EN_ERROR_500);
        }
    }

}
