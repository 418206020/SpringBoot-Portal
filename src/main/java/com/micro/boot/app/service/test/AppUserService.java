package com.micro.boot.app.service.test;

import com.micro.boot.app.dao.UserLoginDao;
import com.micro.boot.app.object.request.UserLoginBean;
import com.micro.boot.common.exception.RRException;
import com.micro.boot.common.validator.Assert;
import com.micro.boot.app.service.ServiceSupport;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by huliang on 2018/1/5.
 */

@Service("appUserService")
public class AppUserService extends ServiceSupport {

    @Autowired
    private UserLoginDao userLoginDao;

    /**
     * 根据手机号查询用户
     *
     * @return
     *
     * @throws Exception
     */
    public HashMap<String, Object> queryByMobile(HashMap<String, Object> param) throws Exception {
        String mobile = param.get("mobile").toString();
        String password = param.get("password").toString();
        HashMap<String, Object> user = findForObject("api.AppUserDao.queryByMobile", mobile);
        UserLoginBean userLoginBean = userLoginDao.queryByMobile2(mobile);
        Assert.isNull(user, "用户不存在");

        //密码错误
        String userpassword = DigestUtils.sha256Hex(password);
        if (!user.get("password").equals(userpassword)) {
            throw new RRException("密码错误");
        }
        return user;
    }

    /**
     * 根据手机号查询用户
     *
     * @return
     *
     * @throws Exception
     */
    public UserLoginBean queryByMobileBean(UserLoginBean param) throws Exception {
        String mobile = param.getMobile();
        String password = param.getPassword();
        UserLoginBean userLoginBean = userLoginDao.queryByMobile2(mobile);
        Assert.isNull(userLoginBean, "用户不存在");

        //密码错误
        String userpassword = DigestUtils.sha256Hex(password);
        if (!userLoginBean.getPassword().equals(userpassword)) {
            throw new RRException("密码错误");
        }
        return userLoginBean;
    }

    /**
     * 注册用户
     *
     * @throws Exception
     */
    public void save(HashMap<String, Object> param) throws Exception {
        String password = param.get("password").toString();
        param.put("password", DigestUtils.sha256Hex(password));
        param.put("createTime", new Date());
        insert("api.AppUserDao.save", param);
    }

}
