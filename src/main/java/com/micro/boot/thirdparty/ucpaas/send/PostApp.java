/**
 * @author Tony
 * @date 2018-01-10
 * @project rest_demo
 */
package com.micro.boot.thirdparty.ucpaas.send;

import java.io.IOException;

import com.micro.boot.common.Constants;
import com.micro.boot.common.utils.PropertiesConfig;
import com.micro.boot.thirdparty.ucpaas.client.AbstractUcpaasClient;
import com.micro.boot.thirdparty.ucpaas.client.UcpaasClient;
import com.micro.boot.thirdparty.ucpaas.template.UcpaasSms;
import com.micro.boot.thirdparty.ucpaas.template.UcpaasTemplate;
import org.apache.commons.lang.StringUtils;


public class PostApp {

    private static UcpaasSms ucpaasSms = new UcpaasSms();
    /**
     * 初始化短信发送配置公共属性
     *
     * @return
     */
    public static final String sid = PropertiesConfig.getInstance()
            .getProperty(Constants.UCPAAS_CONFIG + Constants.SEPPARATOR_DOT + "sid");
    public static final String token = PropertiesConfig.getInstance()
            .getProperty(Constants.UCPAAS_CONFIG + Constants.SEPPARATOR_DOT + "token");
    public static final String appid = PropertiesConfig.getInstance()
            .getProperty(Constants.UCPAAS_CONFIG + Constants.SEPPARATOR_DOT + "appid");
    public static final String templateid = PropertiesConfig.getInstance()
            .getProperty(Constants.UCPAAS_CONFIG + Constants.SEPPARATOR_DOT + "templateid");
    public static String uid = PropertiesConfig.getInstance()
            .getProperty(Constants.UCPAAS_CONFIG + Constants.SEPPARATOR_DOT + "uid");

    public static UcpaasSms InitUcpaasSms() {
        ucpaasSms.setSid(sid);
        ucpaasSms.setToken(token);
        ucpaasSms.setAppid(appid);
        ucpaasSms.setTemplateid(templateid);
        ucpaasSms.setUid(uid);
        return ucpaasSms;
    }

    static AbstractUcpaasClient InstantiationRestAPI() {
        return new UcpaasClient();
    }

    /**
     * 使用系统配置模板发送6位验证码
     *
     * @param userName
     * @param verifyCode
     * @param mobile
     */
    public static void sendSms(String userName, String verifyCode, String mobile) {
        String param = userName + "," + verifyCode;
        testSendSms(null, param, mobile, null);
    }

    /**
     * @param templateid 选填
     * @param param
     * @param mobile
     * @param uid
     */
    private static void testSendSms(String templateid, String param, String mobile, String uid) {
        try {
            ucpaasSms = InitUcpaasSms();
            if (!StringUtils.isEmpty(templateid)) {
                //替换选填模板
                ucpaasSms.setTemplateid(templateid);
            }
            if (!StringUtils.isEmpty(uid)) {
                //替换uid
                ucpaasSms.setUid(uid);
            }
            ucpaasSms.setMobile(mobile);
            ucpaasSms.setParam(param);

            String result = InstantiationRestAPI().sendSms(ucpaasSms);
            System.out.println("Response content is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 群发
     *
     * @param templateid
     * @param param
     * @param mobile
     * @param uid
     */
    private static void testSendSmsBatch(String templateid, String param, String mobile, String uid) {
        try {
            ucpaasSms = InitUcpaasSms();
            if (!StringUtils.isEmpty(templateid)) {
                //替换选填模板
                ucpaasSms.setTemplateid(templateid);
            }
            if (!StringUtils.isEmpty(uid)) {
                //替换uid
                ucpaasSms.setUid(uid);
            }
            ucpaasSms.setMobile(mobile);
            ucpaasSms.setParam(param);
            String result = InstantiationRestAPI().sendSmsBatch(ucpaasSms);
            System.out.println("Response content is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加短信模板
     * 公共参数用使用模板
     *
     * @param sms
     */
    private static void testAddSmsTemplate(UcpaasSms sms, UcpaasTemplate template) {
        try {
            ucpaasSms = InitUcpaasSms();
            if (!StringUtils.isEmpty(templateid)) {
                //替换选填模板
                ucpaasSms.setTemplateid(templateid);
            }
            if (!StringUtils.isEmpty(uid)) {
                //替换uid
                ucpaasSms.setUid(uid);
            }
            ucpaasSms.setMobile(sms.getMobile());
            ucpaasSms.setParam(sms.getParam());
            String result = InstantiationRestAPI().addSmsTemplate(ucpaasSms, template);
            System.out.println("Response content is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void testGetSmsTemplate(UcpaasSms sms, UcpaasTemplate template) {
        try {
            ucpaasSms = InitUcpaasSms();
            if (!StringUtils.isEmpty(templateid)) {
                //替换选填模板
                ucpaasSms.setTemplateid(templateid);
            }

            String result = InstantiationRestAPI().getSmsTemplate(ucpaasSms, template);
            System.out.println("Response content is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void testEditSmsTemplate(UcpaasSms sms, UcpaasTemplate template) {
        try {
            ucpaasSms = InitUcpaasSms();
            if (!StringUtils.isEmpty(templateid)) {
                //替换选填模板
                ucpaasSms.setTemplateid(templateid);
            }
            if (!StringUtils.isEmpty(uid)) {
                //替换uid
                ucpaasSms.setUid(uid);
            }
            String result = InstantiationRestAPI().editSmsTemplate(ucpaasSms, template);
            System.out.println("Response content is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void testDeleterSmsTemplate(String templateid) {
        try {
            ucpaasSms = InitUcpaasSms();
            if (!StringUtils.isEmpty(templateid)) {
                //替换选填模板
                ucpaasSms.setTemplateid(templateid);
            }
            String result = InstantiationRestAPI().deleterSmsTemplate(ucpaasSms);
            System.out.println("Response content is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试说明  启动main方法后，请在控制台输入数字(数字对应 相应的调用方法)，回车键结束
     * 参数名称含义，请参考rest api 文档
     * @throws IOException
     * @method main
     */
//	public static void main(String[] args) throws IOException{
//
//		System.out.println("请输入方法对应的数字(例如1),Enter键结束:");
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//		String methodNumber = br.readLine();
//
//		if (StringUtils.isBlank(methodNumber)){
//			System.out.println("请输入正确的数字，不可为空");
//			return;
//		}
//
//		if (methodNumber.equals("1")) {  //指定模板单发
//			String sid = "";
//			String token = "";
//			String appid = "";
//			String templateid = "";
//			String param = "";
//			String mobile = "";
//			String uid = "";
//			testSendSms(sid, token, appid, templateid, param, mobile, uid);
//		} else if (methodNumber.equals("2")) { //指定模板群发
//			String sid = "";
//			String token = "";
//			String appid = "";
//			String templateid = "";
//			String param = "";
//			String mobile = "";
//			String uid = "";
//			testSendSmsBatch(sid, token, appid, templateid, param, mobile, uid);
//		} else if (methodNumber.equals("3")) {  //增加模板
//			String sid = "";
//			String token = "";
//			String appid = "";
//			String type = "";
//			String template_name = "";
//			String autograph = "";
//			String content = "";
//			testAddSmsTemplate(sid, token, appid, type, template_name, autograph, content);
//		} else if (methodNumber.equals("4")) {  //查询模板
//			String sid = "";
//			String token = "";
//			String appid = "";
//			String templateid = "";
//			String page_num = "";
//			String page_size = "";
//			testGetSmsTemplate(sid, token, appid, templateid, page_num, page_size);
//		} else if (methodNumber.equals("5")) {  //编辑模板
//			String sid = "";
//			String token = "";
//			String appid = "";
//			String templateid = "";
//			String type = "";
//			String template_name = "";
//			String autograph = "";
//			String content = "";
//			testEditSmsTemplate(sid, token, appid, templateid, type, template_name, autograph, content);
//		} else if (methodNumber.equals("6")) {  //删除模板
//			String sid = "";
//			String token = "";
//			String appid = "";
//			String templateid = "";
//			testDeleterSmsTemplate(sid, token, appid, templateid);
//		}
//	}
}
