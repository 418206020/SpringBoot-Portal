package com.micro.boot.api;

import com.micro.boot.app.object.McAddress;
import com.micro.boot.app.object.request.device.McDeviceReq;
import com.micro.boot.app.object.request.user.McPasswordResetReq;
import com.micro.boot.app.object.request.user.McUserInfoReq;
import com.micro.boot.app.object.request.user.McUserLoginReq;
import com.micro.boot.app.object.request.user.McUserRegisterReq;
import com.micro.boot.common.AppRestUrl;
import com.micro.boot.common.Constants;
import com.micro.boot.common.ModuleConstant;
import com.micro.boot.common.request.BodyInfo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
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
public class DeviceTest {

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    private static final String Url_Preffix = "/app/v1.0.0";
    private static final String TOKEN = "b42a1880c7057bed7fea392cb9ebc711";
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
    public void test_device_1_post() throws Exception {
        //--------------------构造测试数据------------------------
        McDeviceReq req = new McDeviceReq();
        req.setDevType("NC");
        req.setDevMacid("macid-293s2");
        req.setDevMode(2);
        req.setDevNameEn("english");
        req.setDevNameZh("中文名称");
        req.setDevStatus(1);
        McAddress address = new McAddress();
        address.setType("CN");
        address.setUndefNation("86");//中国
        address.setIsDefined(1);//不适用自定义
        address.setUndefProvince("610000");//陕西省
        address.setUndefCity("610500");//渭南市
        address.setUndefCounty("610581");//韩城市
        address.setDefAddress("详细地址");
        address.setCoordinate("0.0.0.0.0");//卫星定位坐标
        req.setMcAddress(address);
        //--------------------构造测试数据------------------------
        String bodyContent = JSONObject.fromObject(getData(req).toString()).toString();
        System.out.println("TEST-REQUEST-DATA:" + getData(req).toString());
        ResultActions perform = mvc.perform(MockMvcRequestBuilders
                .post(Url_Preffix+ Constants.SEPPARATOR_SLASH + ModuleConstant.MODULE_DEVICE
                        + Constants.SEPPARATOR_SLASH+ AppRestUrl.MC_DEVICE_ADD)
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
     *
     * @throws Exception
     */
    @Test
    public void test_device_2_get() throws Exception {
        ResultActions perform = mvc.perform(MockMvcRequestBuilders
                .get(Url_Preffix + "/device/info/macid-293s2")
                .header(Constants.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8)
                .header("token", TOKEN)
                .header("mobile", MOBILE)
                .content("")
        );
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }
    /**
     * 修改
     * @throws Exception
     */
    @Test
    public void test_device_3_post() throws Exception {
        //--------------------构造测试数据------------------------
        McDeviceReq req = new McDeviceReq();
        req.setDevType("NC");
        req.setDevMacid("macid-293s2");
        req.setDevMode(2);
        req.setDevNameEn("englishName");
        req.setDevNameZh("中文名称");
        req.setDevStatus(1);
        McAddress address = new McAddress();
        address.setType("CN86");
        address.setUndefNation("86");//中国
        address.setIsDefined(1);//不适用自定义
        address.setUndefProvince("610000");//陕西省
        address.setUndefCity("610500");//渭南市
        address.setUndefCounty("610581");//韩城市
        address.setDefAddress("详细地址..");
        address.setCoordinate("0.0.0.0.0");//卫星定位坐标
        req.setMcAddress(address);
        //--------------------构造测试数据------------------------
        String bodyContent = JSONObject.fromObject(getData(req).toString()).toString();
        System.out.println("TEST-REQUEST-DATA:" + getData(req).toString());
        ResultActions perform = mvc.perform(MockMvcRequestBuilders
                .post(Url_Preffix+ Constants.SEPPARATOR_SLASH + ModuleConstant.MODULE_DEVICE
                        + Constants.SEPPARATOR_SLASH+ AppRestUrl.MC_DEVICE_EDIT)
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
    public void test_device_4_delete() throws Exception {
        ResultActions perform = mvc.perform(MockMvcRequestBuilders
                .delete(Url_Preffix + "/device/info/macid-293s2")
                .header(Constants.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8)
                .header("token", TOKEN)
                .header("mobile", MOBILE)
                .content("")
        );
        perform.andExpect(MockMvcResultMatchers.status().isOk());
    }


}