package com.micro.boot.api;

import com.micro.boot.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.alibaba.fastjson.JSONObject;

/**
 * 〈测试接口〉
 *
 * @author Administrator
 * @create 2018/4/12
 * @since 1.0.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class PostmanTest {

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    private static final String Url_Preffix = "/app/v1.0.0";

    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testInfo() throws Exception {
        ResultActions perform = mvc.perform(MockMvcRequestBuilders
                .get(Url_Preffix + "/register/sms/15094011640"));
        perform.andExpect(MockMvcResultMatchers.status().isOk());
        perform.andExpect(MockMvcResultMatchers.jsonPath("$.version").value("v1.0.0"));
//        perform.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("请求成功"));
        Object data = perform.andReturn().getResponse().getContentAsString();
        System.out.println(data.toString());


    }

}