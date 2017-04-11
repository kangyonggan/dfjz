package com.kangyonggan.app.dfjz.biz.util;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.Properties;

/**
 * app.properties工具类
 *
 * @author kangyonggan
 * @since 2016/12/6
 */
@Log4j2
public class PropertiesUtil {

    private static Properties props;

    static {
        refresh();
    }

    private PropertiesUtil() {
    }

    public static void refresh() {
        InputStream in = null;
        FileInputStream fis = null;
        try {
            props = new Properties();
            in = PropertiesUtil.class.getClassLoader().getResourceAsStream("app.properties");
            InputStreamReader reader = new InputStreamReader(in, "UTF-8");
            props.load(reader);

            String propertiesPath = props.getProperty("properties.path");
            log.info("本地配置文件路径:{}", propertiesPath);

            fis = new FileInputStream(new File(propertiesPath));
            props.load(fis);
            log.info("本地配置文件加载完毕");
        } catch (IOException e) {
            log.error("配置文件加载失败", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    log.error("关闭输入流异常", e);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    log.error("关闭输入流异常", e);
                }
            }
        }
    }

    /**
     * 获取properties的值，默认值""
     *
     * @param name
     * @return
     */
    public static String getProperties(String name) {
        return getPropertiesWithDefault(props.getProperty("app.shortname") + "." + name, "");
    }

    /**
     * 获取properties的值
     *
     * @param name
     * @param defaultValue 默认值
     * @return
     */
    public static String getPropertiesWithDefault(String name, String defaultValue) {
        return props.getProperty(name, defaultValue);
    }

}

