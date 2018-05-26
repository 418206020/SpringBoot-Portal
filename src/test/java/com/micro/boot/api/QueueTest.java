package com.micro.boot.api;

import com.micro.boot.app.dao.McTopicDao;
import com.micro.boot.app.object.request.msg.McMsgReq;
import com.micro.boot.app.object.request.subscribe.McSubscribeReq;
import com.micro.boot.app.object.response.subscribe.McSubscribeRep;
import com.micro.boot.app.service.queue.McSubscriberService;
import com.micro.boot.common.AppRestUrl;
import com.micro.boot.common.Constants;
import com.micro.boot.common.ModuleConstant;
import com.micro.boot.common.request.BodyInfo;
import com.micro.boot.common.utils.DateUtils;
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

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
public class QueueTest {

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    private static final String Url_Preffix = "/app/v1.0.0";
    private static final String TOKEN = "8c0a0494833bf26228142b64b750246b";
    private static final String MOBILE = "15094011640";
    private static final String CODE_VERIFY = "111222";

    @Resource
    private McSubscriberService mcSubscriberService;

    @Resource
    private McTopicDao mcTopicDao;

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
    public void test_Subscribe_1() throws Exception {
        //--------------------构造测试数据------------------------
        McSubscribeReq req = new McSubscribeReq();
        req.setStatus("1");
        req.setClientid(String.valueOf(System.currentTimeMillis()));//所属设备id
        req.setTopics("/m2m/#");
        req.setSubTime(new Date());

        String[] topics = {Constants.M2M + "#"};
        mcSubscriberService.subscriber(String.valueOf(System.currentTimeMillis()),topics);
        List<McSubscribeRep> list = mcSubscriberService.listSubscriber(req);
        mcSubscriberService.unsubscribe();
        list = mcSubscriberService.listSubscriber(req);

    }

    @Test
    public void test_delete(){
        Date beforeDate = DateUtils.getOneMinuteBefore(new Date(System.currentTimeMillis()));
        McMsgReq req = new McMsgReq();
        req.setTimeConsumer(DateUtils.getSqlDateByUtilDate(beforeDate));
        mcTopicDao.deleteHistory(req);
    }


}