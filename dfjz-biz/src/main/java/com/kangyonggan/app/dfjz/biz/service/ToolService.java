package com.kangyonggan.app.dfjz.biz.service;

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
     */
    String formatXml(String data);

    /**
     * 格式化SQL
     *
     * @param data
     * @param dialect
     * @return
     */
    String formatSql(String data, String dialect);
}