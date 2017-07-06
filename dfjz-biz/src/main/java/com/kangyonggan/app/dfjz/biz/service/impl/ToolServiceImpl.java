package com.kangyonggan.app.dfjz.biz.service.impl;

import com.alibaba.druid.sql.SQLUtils;
import com.kangyonggan.app.dfjz.biz.service.ToolService;
import com.kangyonggan.app.dfjz.common.XmlUtil;
import com.kangyonggan.app.dfjz.model.annotation.LogTime;
import com.kangyonggan.app.dfjz.model.constants.Dialect;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author kangyonggan
 * @since 2017/4/22 0022
 */
@Service
public class ToolServiceImpl implements ToolService {

    @Override
    @LogTime
    public String formatXml(String data) throws Exception {
        if (StringUtils.isEmpty(data)) {
            return "xml不能为空";
        }

        return XmlUtil.format(data);
    }

    @Override
    @LogTime
    public String formatSql(String data, String dialect) throws Exception {
        if (StringUtils.isEmpty(data)) {
            return "sql不能为空";
        }

        if (StringUtils.isEmpty(dialect)) {
            dialect = "MySQL";
        }

        if (Dialect.MySQL.getDialect().equals(dialect)) {
            return SQLUtils.formatMySql(data);
        } else if (Dialect.Oracle.getDialect().equals(dialect)) {
            return SQLUtils.formatOracle(data);
        } else if (Dialect.SQLServer.getDialect().equals(dialect)) {
            return SQLUtils.formatSQLServer(data);
        } else {
            return "不支持方言";
        }
    }
}
