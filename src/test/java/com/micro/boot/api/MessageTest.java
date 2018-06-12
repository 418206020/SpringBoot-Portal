package com.micro.boot.api;

import com.micro.boot.app.object.McAddress;
import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.common.AppRestUrl;
import com.micro.boot.common.Constants;
import com.micro.boot.common.ModuleConstant;
import com.micro.boot.common.request.BodyInfo;
import net.sf.json.JSONObject;
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
public class MessageTest {

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    private static final String Url_Preffix = "/app/v1.0.0";
    private static final String TOKEN = "8c0a0494833bf26228142b64b750246b";
    private static final String MOBILE = "15094011640";
    private static final String CODE_VERIFY = "111222";

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
    public void test_message_1_post() throws Exception {
        //--------------------构造测试数据------------------------
        McMsgReq req = new McMsgReq();
        req.setUserid(1l);
        req.setDevid(29l);//所属设备id
        req.setMsgType("225");
        req.setMsgSize("256k");
        req.setRequestQos("Qos");
        req.setStatus(1);
        req.setSuback("suback msg");
        req.setTimeProducer(new Date());
        req.setTopId(1l);

        //--------------------构造测试数据------------------------
        String bodyContent = JSONObject.fromObject(getData(req).toString()).toString();
        System.out.println("TEST-REQUEST-DATA:" + getData(req).toString());
        ResultActions perform = mvc.perform(MockMvcRequestBuilders
                .post(Url_Preffix + Constants.SEPPARATOR_SLASH + ModuleConstant.MODULE_MSG
                        + Constants.SEPPARATOR_SLASH + AppRestUrl.MC_MSG_ADD)
                .header(Constants.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8)
                .header(Constants.ACCEPT_LANGUAGE, Constants.LANG_ZH_CN)
                .header("token", TOKEN)
                .header("mobile", MOBILE)
                .content(bodyContent)
        );
        //输出返回
        System.out.println(perform.andReturn().getResponse().getContentAsString());
        //根据条件校验是否成功
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"));
    }

    /**
     * @throws Exception
     */
    @Test
    public void test_message_2_get() throws Exception {
        String msgId = "2";
        ResultActions perform = mvc.perform(MockMvcRequestBuilders
                .get(Url_Preffix + "/message/info/" + msgId)
                .header(Constants.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8)
                .header("token", TOKEN)
                .header("mobile", MOBILE)
                .content("")
        );
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * 修改
     *
     * @throws Exception
     */
    @Test
    public void test_message_3_post() throws Exception {
        //--------------------构造测试数据------------------------
        String msgId = "2";
        McMsgReq req = new McMsgReq();
        req.setId(Long.valueOf(msgId));
        req.setUserid(1l);
        req.setMsgType("2256");
        req.setMsgSize("512k");
        req.setRequestQos("Qos update");
        req.setStatus(1);
        req.setSuback("suback msg.");
        req.setTimeProducer(new Date());
        req.setTopId(1l);

        //--------------------构造测试数据------------------------
        String bodyContent = JSONObject.fromObject(getData(req).toString()).toString();
        System.out.println("TEST-REQUEST-DATA:" + getData(req).toString());
        ResultActions perform = mvc.perform(MockMvcRequestBuilders
                .post(Url_Preffix + Constants.SEPPARATOR_SLASH + ModuleConstant.MODULE_MSG
                        + Constants.SEPPARATOR_SLASH + AppRestUrl.MC_MSG_EDIT)
                .header(Constants.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8)
                .header(Constants.ACCEPT_LANGUAGE, Constants.LANG_ZH_CN)
                .header("token", TOKEN)
                .header("mobile", MOBILE)
                .content(bodyContent)
        );
        //输出返回
        System.out.println(perform.andReturn().getResponse().getContentAsString());
        //根据条件校验是否成功
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"));
    }

    @Test
    public void test_message_4_delete() throws Exception {
        String msgId = "1";
        ResultActions perform = mvc.perform(MockMvcRequestBuilders
                .delete(Url_Preffix + "/message/info/" + msgId)
                .header(Constants.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8)
                .header("token", TOKEN)
                .header("mobile", MOBILE)
                .content("")
        );
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }


}