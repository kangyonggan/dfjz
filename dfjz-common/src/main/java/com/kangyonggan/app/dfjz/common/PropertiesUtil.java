package com.kangyonggan.app.dfjz.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author kangyonggan
 * @since 7/12/17
 */
public class PropertiesUtil {

    /**
     * 读取配置文件
     *
     * @param in 配置文件的输入流
     * @return 编码
     * @throws Exception
     */
    public static Properties readProperties(InputStream in, String charset) throws Exception {
        Properties props = new Properties();
        try {
            InputStreamReader reader = new InputStreamReader(in, charset);
            props.load(reader);
        } catch (Exception e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return props;
    }

    /**
     * 读取配置文件
     *
     * @param in 配置文件的输入流
     * @throws Exception
     */
    public static Properties readProperties(InputStream in) throws Exception {
        return readProperties(in, "UTF-8");
    }

    /**
     * 读取配置文件
     *
     * @param fileName 配置文件的全路径
     * @return 编码
     * @throws Exception
     */
    public static Properties readProperties(String fileName, String charset) throws Exception {
        return readProperties(new FileInputStream(fileName), charset);
    }

    /**
     * 读取配置文件
     *
     * @param fileName 配置文件的全路径
     * @return 返回配置的名值对
     * @throws Exception
     */
    public static Properties readProperties(String fileName) throws Exception {
        return readProperties(fileName, "UTF-8");
    }

}
