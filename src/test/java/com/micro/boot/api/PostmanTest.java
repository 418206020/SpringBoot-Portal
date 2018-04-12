package com.micro.boot.api;

import com.micro.boot.app.object.request.user.McUserRegisterReq;
import com.micro.boot.common.Constants;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.utils.PwdTools;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

/**
 * 〈测试接口〉
 * 方法名格式：test_<排序号>_<请求方式post、get、put等>_模块名
 *
 * @author Administrator
 * @create 2018/4/12
 * @since 1.0.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //按方法名排序执行
@Transactional //SQL
public class PostmanTest {

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    private static final String Url_Preffix = "/app/v1.0.0";

    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * 生成加密请求数据
     */
    public static BodyInfo getData(Object object) {
        return BodyInfo.build(Constants.VERSION_APP, object);
    }

    @Test
    public void test_1_get_register() throws Exception {
        String pathParam = "15094011640";
        ResultActions perform = mvc.perform(MockMvcRequestBuilders
                .get(Url_Preffix + "/register/sms/" + pathParam)
                .header(Constants.CONTENT_TYPE,MediaType.APPLICATION_JSON_UTF8)
        );
        //输出返回
        System.out.println(perform.andReturn().getResponse().getContentAsString());
        //根据条件校验是否成功
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.version").value("v1.0.0"));

    }

    @Test
    public void test_2_post_register() throws Exception {
        //构造测试数据
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
//        String bodyContent = JSONObject.fromObject(getData(req).toString());
        String bodyContent = getData(req).toString();
        ResultActions perform = mvc.perform(MockMvcRequestBuilders
                .post(Url_Preffix + "/register/register/mobile")
                .header(Constants.CONTENT_TYPE,MediaType.APPLICATION_JSON_UTF8)
                .content(bodyContent)
        );
        //输出返回
        System.out.println(perform.andReturn().getResponse().getContentAsString());
        //根据条件校验是否成功
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.version").value("v1.0.0"));
    }

}