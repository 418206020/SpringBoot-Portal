package com.micro.boot.thirdparty.ucpaas.http;

import com.micro.boot.common.Constants;
import com.micro.boot.common.utils.Constant;
import com.micro.boot.common.utils.PropertiesConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.TIMEOUT;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

public class HttpClientUtil {

    //超时
    private static final int TIMEOUT = 10000;

    public String postJson(String url, String body, String charset) {
        String result = null;
        if (PropertiesConfig.getInstance().getProperty(Constants.UCPAAS_CONFIG + Constants.SEPPARATOR_DOT + "open")
                .equals("true"))
        {
            if (null == charset) {
                charset = Constants.CHARSET_NAME;
            }
            CloseableHttpClient httpClient = null;
            HttpPost httpPost = null;
            try {
                httpClient = HttpConnectionManager.getInstance().getHttpClient();
                httpPost = new HttpPost(url);

                // 设置连接超时,设置读取超时
                RequestConfig requestConfig = RequestConfig.custom()
                        .setConnectTimeout(TIMEOUT)
                        .setSocketTimeout(TIMEOUT)
                        .build();
                httpPost.setConfig(requestConfig);

                httpPost.setHeader("Accept", Constants.APPLICATION_JSON);
                httpPost.setHeader("Content-Type", Constants.APPLICATION_JSON_UTF8);

                // 设置参数
                StringEntity se = new StringEntity(body, charset);
                httpPost.setEntity(se);
                //TODO 测试中，注释以下代码，发短信要钱啊
                HttpResponse response = httpClient.execute(httpPost);
                if (response != null) {
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        result = EntityUtils.toString(resEntity, charset);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            result = "config.properties中 open 属性值为false, 若已正确设置请求值，请设置为true后打开发送";
        }

        return result;
    }

}
