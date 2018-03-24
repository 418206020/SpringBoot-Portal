package com.micro.boot.common.utils;

import com.micro.boot.common.Constants;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;

/**
 * config.properties 配置
 */
public class PropertiesConfig {
    private Properties props = null;
    private static volatile PropertiesConfig conf;

    private PropertiesConfig() {
        props = new Properties();
        loadConfigProps();
    }

    public static PropertiesConfig getInstance() {
        if (conf == null) {
            synchronized (PropertiesConfig.class) {
                if (conf == null) {
                    conf = new PropertiesConfig();
                }
            }
        }
        return conf;
    }

    public void loadConfigProps() {
        InputStream is = null;
        try {
            is = getClass().getResourceAsStream(Constants.WEB_INF_CLASSES + Constants.PROP_CONFIG);
            if (is == null) {
                is = getClass().getResourceAsStream(Constants.PROP_CONFIG);
            }
            InputStreamReader reader = new InputStreamReader(is, Constants.CHARSET_NAME);
            props.load(reader);
            Iterator<String> iter = props.stringPropertyNames().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                props.setProperty(key, props.getProperty(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getProperty(String key) {
        String tmp = props.getProperty(key);
        if (StringUtils.isNotEmpty(tmp)) {
            return tmp.trim();
        }
        return tmp;
    }

    public String getProperty(String key, String defaultValue) {
        String tmp = props.getProperty(key, defaultValue);
        if (StringUtils.isNotEmpty(tmp)) {
            return tmp.trim();
        }
        return tmp;
    }

    public int getPropertyInt(String key) {
        String tmp = props.getProperty(key);
        if (StringUtils.isNotEmpty(tmp)) {
            return Integer.parseInt(tmp.trim());
        }
        return 0;

    }

    public int getPropertyInt(String key, int defaultValue) {
        String tmp = props.getProperty(key);
        if (StringUtils.isNotEmpty(tmp)) {
            return Integer.parseInt(tmp.trim());
        }
        return defaultValue;
    }

    public long getPropertyLong(String key, long defaultValue) {
        String tmp = props.getProperty(key);
        if (StringUtils.isNotEmpty(tmp)) {
            return Integer.parseInt(tmp.trim());
        }
        return defaultValue;
    }
}
