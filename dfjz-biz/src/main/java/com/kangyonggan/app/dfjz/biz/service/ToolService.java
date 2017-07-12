package com.kangyonggan.app.dfjz.biz.service;

import java.io.InputStream;

/**
 * @author kangyonggan
 * @since 2017/4/22 0022
 */
public interface ToolService {

    /**
     * 格式化xml
     *
     * @param data
     * @return
     * @throws Exception
     */
    String formatXml(String data) throws Exception;

    /**
     * 格式化SQL
     *
     * @param data
     * @param dialect
     * @return
     * @throws Exception
     */
    String formatSql(String data, String dialect) throws Exception;

    /**
     * properties文件对比
     *
     * @param inLeft
     * @param inRight
     * @return
     * @throws Exception
     */
    String compareProperties(InputStream inLeft, InputStream inRight) throws Exception;
}
