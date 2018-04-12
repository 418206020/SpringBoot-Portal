package com.micro.boot;

import com.google.gson.Gson;
import com.micro.boot.app.object.request.user.McPasswordResetReq;
import com.micro.boot.app.object.request.user.McUserLoginReq;
import com.micro.boot.app.object.request.user.McUserRegisterReq;
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

        String salt = "5G1Fueti2BkHY0MBDnFY";
        String apwd = "DK_OWK39DK";
        String ppwd = new Sha256Hash(DigestUtils.sha256Hex(apwd), salt).toHex();
        System.out.println(ppwd);
        ppwd = new Sha256Hash(DigestUtils.sha256Hex(apwd), salt).toHex();
        System.out.println(ppwd);


////        boolean isDecrpt = true;//解密
//        boolean isDecrpt = false;//加密
//        if (isDecrpt) {
//            decrypData();
//        } else {
//            BodyInfo bodyInfo = bodyinfo();//生成加密后的“请求数据”；快速构造测试数据
//            System.out.println("JsonData:" + "\n" + JSONObject.fromObject(bodyInfo.toString()) + "\n");
//        }
        return;
    }

    /**
     * 构造数据:
     * 请实现具体的构造数据方法，比如：getRegisterReq()
     *
     * @return
     */
    private static Object generator() {
//        data = getRegisterReq();//注册信息 aa471672b7503a6d99d3d1b1342d7aabb9bd28faf3738de4f44d8e48c9c99c3f
//        data = getPasswordRest();//忘记密码重置请求
//        data=getLoginReq();//登录信息
        data = getUpdatePwd();//修改密码

        return data;
    }

    private static Object getUpdatePwd() {
        //其余在header
        McPasswordResetReq req = new McPasswordResetReq();
//        String pwd = "Tui_0ke_s";
        String pwd = "11111111";
//        if(PwdTools.isCorrect_1_8(pwd)){
            req.setPassword(pwd);
//        }

        return req;
    }

    private static McPasswordResetReq getPasswordRest() {
        McPasswordResetReq req = new McPasswordResetReq();
        req.setMobile("15094011640");
        req.setVerifyCode("111222");
        req.setPassword("abc123");//这个写不写无所谓，重置给默认6个1
        return req;
    }


    public Test() {
        return;
    }

    /**
     * 解密
     */
    private static void decrypData() {
        //测试返回的结果
        ReturnAppInfo returnAppInfo = new ReturnAppInfo();
//-------------------------------------------------------------------------------------/
        //解密短信验证码
        data = "9JsHADF68OHNveYqwQF2yg==";//copy of postman
        returnAppInfo.setData((String) data);
        String response = new Gson().fromJson(returnAppInfo.decryptData(), String.class);
        System.out.print("response=" + response.toString());
//        //注册用户返回结果-包含密码
//        data = "l+t81Q6se6jCEoXGZbO+yxSUfE6Do2g9DoAvvNoQhYiLnWlMQCCbFP+Hfv/vDLCGOhxs6y3vCqaHHogKgjnRODlN8r0DEZpU9VZmX+E0DnE5TMwqZUln2iPjU2CZIOWP6WTt6uSJxY7gKBGGKqGjoyKjrK/ZeeGaVxI5g5tMDvqcrrOSrDe+AMo3pEklip6kwDD+mF/b8U+8KACqhmNhp3c6W952C41LHeT4b5/QqTnKQZcWx2+mlu/bnpkq5bIhAA00GFra+1Of834+hSSBB4ffsUvxz/UK11cSHJ/bzc8=";
//        returnAppInfo.setData((String) data);
//        McUserRegisterReq response = new Gson().fromJson(returnAppInfo.decryptData(), McUserRegisterReq.class);
//        System.out.println("userId:" +response.getId()+ "\npassword:" + response.getPassword() + "\n");//输出重要参数
//        // 注册用户返回结果-包含密码
//        data = "n1oC3TmPQ6u1yR0oc+MYDZ7NWRIJ2PnyxmuykZ0EmMWidpY7R8mf2/IFWIhqLZMGtJzPm5CgRnt+wZ9mSLEoL9oVemZqZkWVdZreW70E0Pyz8SMcUUQlc0wv2e74hoW+QlCo91JEMLkBI7G/wFMffvYHjKoU2yttRhkUui1veWG8AYI5MtswRzvCM6FJkiW/9PUvoxQW1b9jH3pkA0o0LQgqcgKKPsOO0/fxFhApuPUfMyhKX8uNJ6WYpRRI3I17GGYVrdGDuzBo+Dyi+mrh6A9klNdfpnhg8DB2oFQBYBggY/QDJThlYuMNGnpkrn1qLd865txpKlM=";
//        returnAppInfo.setData((String) data);
//        McUserInfoRep response = new Gson().fromJson(returnAppInfo.decryptData(), McUserInfoRep.class);
//        System.out.println("userId:" +response.getId()+ "\npassword:" + response.getPassword() + "\n");//输出重要参数
    }


    /**
     * 生成加密请求数据
     */
    public static BodyInfo bodyinfo() {
        return BodyInfo.build(Constants.VERSION_APP, generator());
    }

    /**
     * 生成加密返回数据
     */
    public static ReturnAppInfo returninfo() {
        return ReturnAppInfo.successEncrypt(generator());
    }

    /**
     * 注册信息
     *
     * @return
     */
    private static McUserRegisterReq getRegisterReq() {
        McUserRegisterReq req = new McUserRegisterReq();
        req.setMobile("15094011640");
        req.setVerifyCode("111222");
        req.setCreateTime(new Date());
        //密码加盐参数
        req.setSalt(RandomStringUtils.randomAlphanumeric(Constants.COUNT_SALT));
        req.setNickname("夜火阑珊");
        req.setUsername("DK_OWK39DK");
        //用户名只允许英文数字和下划线
        if (!PwdTools.isCorrect_1_8(req.getUsername())) {
            req.setUsername(req.getUsername().replaceAll("-", "_"));
        }
        req.setEmail("418206020@11.com");
        req.setSex("1");//1：男；2：女
        return req;
    }

    /**
     * 登录信息
     *
     * @return
     */
    private static McUserLoginReq getLoginReq() {
        McUserLoginReq req = new McUserLoginReq();
        req.setMobile("15094011640");
//        两种方式最少选一种
        req.setPassword("11111111");//密码传输
//        req.setVerifyCode("111222");
        return req;
    }


}