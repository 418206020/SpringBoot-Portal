package com.micro.boot;

import com.google.gson.Gson;
import com.micro.boot.app.object.McRequestPage;
import com.micro.boot.app.object.request.user.McPasswordResetReq;
import com.micro.boot.app.object.request.user.McUserLoginReq;
import com.micro.boot.app.object.request.user.McUserRegisterReq;
import com.micro.boot.app.utils.AppUtils;
import com.micro.boot.common.Constants;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.response.ReturnAppInfo;
import com.micro.boot.common.utils.PwdTools;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

import java.util.Date;

/**
 * 〈〉
 *
 * @author Administrator
 * @create 2018/4/2
 * @since 1.0.0
 */
public class Test {

    private static Object data;

    public static void main(String[] args) {

        McRequestPage page = new McRequestPage();
        page.setOrderBy("createTime");
        page.setOrderDesc("asc");
//        ;
        System.out.println("00"+AppUtils.getOrderString(page));


//        McUserLoginReq req = new McUserLoginReq();
//        req.setMobile("15094011640");
//        req.setPassword("DK_OWK39DK");//密码传输
//        //--------------------构造测试数据------------------------
//        String bodyContent = JSONObject.fromObject(getData(req).toString()).toString();
//        System.out.println("TEST-REQUEST-DATA:" + getData(req).toString());
//
//



        return;
    }

    /**
     * 生成加密请求数据
     */
    public static BodyInfo getData(Object object) {
        return BodyInfo.build(Constants.VERSION_APP, object);
    }

}