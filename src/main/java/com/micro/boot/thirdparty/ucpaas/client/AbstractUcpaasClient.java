/**
 * @author huliang
 * @date 2017-05-12
 * @project ucpaas
 */
package com.micro.boot.thirdparty.ucpaas.client;

import com.micro.boot.common.Constants;
import com.micro.boot.common.utils.PropertiesConfig;
import com.micro.boot.thirdparty.ucpaas.template.UcpaasSms;
import com.micro.boot.thirdparty.ucpaas.template.UcpaasTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ucpaas client
 */
public abstract class AbstractUcpaasClient {

    /**
     * @param ucpaasSms
     *
     * @return
     */
    public abstract String sendSms(UcpaasSms ucpaasSms);

    /**
     * @param ucpaasSms
     *
     * @return
     */
    public abstract String sendSmsBatch(UcpaasSms ucpaasSms);

    /**
     * @param ucpaasSms
     * @param ucpaasTemplate
     *
     * @return
     */
    public abstract String addSmsTemplate(UcpaasSms ucpaasSms, UcpaasTemplate ucpaasTemplate);

    /**
     * @param ucpaasSms
     * @param ucpaasTemplate
     *
     * @return
     */
    public abstract String getSmsTemplate(UcpaasSms ucpaasSms, UcpaasTemplate ucpaasTemplate);

    /**
     * @param ucpaasSms
     * @param ucpaasTemplate
     *
     * @return
     */
    public abstract String editSmsTemplate(UcpaasSms ucpaasSms, UcpaasTemplate ucpaasTemplate);

    /**
     * @param ucpaasSms
     *
     * @return
     */
    public abstract String deleterSmsTemplate(UcpaasSms ucpaasSms);


    public StringBuffer getStringBuffer() {
        StringBuffer sb = new StringBuffer(Constants.URL_HTTPS);
        //UCPAAS 返回地址 https://open.ucpaas.com/ol/sms/{function}
        sb.append(PropertiesConfig.getInstance().getProperty(Constants.UCPAAS_CONFIG+Constants.SEPPARATOR_DOT+"restServer")).append("/ol/sms");
        return sb;
    }

}
