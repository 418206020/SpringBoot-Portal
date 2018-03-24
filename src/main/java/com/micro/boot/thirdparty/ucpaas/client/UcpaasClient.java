
package com.micro.boot.thirdparty.ucpaas.client;


import com.alibaba.fastjson.JSONObject;
import com.micro.boot.thirdparty.ucpaas.http.HttpClientUtil;
import com.micro.boot.thirdparty.ucpaas.template.UcpaasSms;
import com.micro.boot.thirdparty.ucpaas.template.UcpaasTemplate;
import org.slf4j.LoggerFactory;

/**
 * 短信和短信模板操作
 */

public class UcpaasClient extends AbstractUcpaasClient {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    private static HttpClientUtil httpClientUtil  = new HttpClientUtil();

    @Override
    public String sendSms(UcpaasSms ucpaasSms)
    {

        String result = "";

        try {
            String url = getStringBuffer().append("/sendsms").toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", ucpaasSms.getSid());
            jsonObject.put("token", ucpaasSms.getToken());
            jsonObject.put("appid", ucpaasSms.getAppid());
            jsonObject.put("templateid", ucpaasSms.getTemplateid());
            jsonObject.put("param", ucpaasSms.getParam());
            jsonObject.put("mobile", ucpaasSms.getMobile());
            jsonObject.put("uid", ucpaasSms.getUid());

            String body = jsonObject.toJSONString();
            logger.info(body);
            result = httpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String sendSmsBatch(UcpaasSms ucpaasSms)
    {

        String result = "";

        try {
            String url = getStringBuffer().append("/sendsms_batch").toString();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", ucpaasSms.getSid());
            jsonObject.put("token", ucpaasSms.getToken());
            jsonObject.put("appid", ucpaasSms.getAppid());
            jsonObject.put("templateid", ucpaasSms.getTemplateid());
            jsonObject.put("param", ucpaasSms.getParam());
            jsonObject.put("mobile", ucpaasSms.getMobile());
            jsonObject.put("uid", ucpaasSms.getUid());

            String body = jsonObject.toJSONString();

            System.out.println("body = " + body);

            result = httpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String addSmsTemplate(UcpaasSms ucpaasSms, UcpaasTemplate ucpaasTemplate)
    {

        String result = "";

        try {
            String url = getStringBuffer().append("/addsmstemplate").toString();

            JSONObject jsonObject = new JSONObject();
            //sms
            jsonObject.put("sid", ucpaasSms.getSid());
            jsonObject.put("token", ucpaasSms.getToken());
            jsonObject.put("appid", ucpaasSms.getAppid());
            jsonObject.put("templateid", ucpaasSms.getTemplateid());
            jsonObject.put("param", ucpaasSms.getParam());
            jsonObject.put("mobile", ucpaasSms.getMobile());
            jsonObject.put("uid", ucpaasSms.getUid());
            //template
            jsonObject.put("type", ucpaasTemplate.getType());
            jsonObject.put("template_name", ucpaasTemplate.getTemplate_name());
            jsonObject.put("autograph", ucpaasTemplate.getAutograph());
            jsonObject.put("content", ucpaasTemplate.getContent());

            String body = jsonObject.toJSONString();
            result = httpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getSmsTemplate(UcpaasSms ucpaasSms, UcpaasTemplate ucpaasTemplate)
    {

        String result = "";

        try {
            String url = getStringBuffer().append("/getsmstemplate").toString();

            JSONObject jsonObject = new JSONObject();
            //sms
            jsonObject.put("sid", ucpaasSms.getSid());
            jsonObject.put("token", ucpaasSms.getToken());
            jsonObject.put("appid", ucpaasSms.getAppid());
            jsonObject.put("templateid", ucpaasSms.getTemplateid());

            jsonObject.put("page_num", ucpaasTemplate.getPage_num());
            jsonObject.put("page_size", ucpaasTemplate.getPage_size());

            String body = jsonObject.toJSONString();
            result = httpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String editSmsTemplate(UcpaasSms ucpaasSms, UcpaasTemplate ucpaasTemplate)
    {

        String result = "";

        try {
            String url = getStringBuffer().append("/editsmstemplate").toString();

            JSONObject jsonObject = new JSONObject();
            //sms
            jsonObject.put("sid", ucpaasSms.getSid());
            jsonObject.put("token", ucpaasSms.getToken());
            jsonObject.put("appid", ucpaasSms.getAppid());
            jsonObject.put("templateid", ucpaasSms.getTemplateid());

            //template
            jsonObject.put("type", ucpaasTemplate.getType());
            jsonObject.put("template_name", ucpaasTemplate.getTemplate_name());
            jsonObject.put("autograph", ucpaasTemplate.getAutograph());
            jsonObject.put("content", ucpaasTemplate.getContent());

            String body = jsonObject.toJSONString();
            result = httpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String deleterSmsTemplate(UcpaasSms ucpaasSms) {

        String result = "";

        try {
            String url = getStringBuffer().append("/deletesmstemplate").toString();

            JSONObject jsonObject = new JSONObject();
            //sms
            jsonObject.put("sid", ucpaasSms.getSid());
            jsonObject.put("token", ucpaasSms.getToken());
            jsonObject.put("appid", ucpaasSms.getAppid());
            jsonObject.put("templateid", ucpaasSms.getTemplateid());

            String body = jsonObject.toJSONString();
            result = httpClientUtil.postJson(url, body, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
