package com.kangyonggan.app.dfjz.biz.service.impl;

import com.alibaba.druid.sql.SQLUtils;
import com.kangyonggan.app.dfjz.biz.service.ToolService;
import com.kangyonggan.app.dfjz.common.CalendarUtil;
import com.kangyonggan.app.dfjz.common.DestinyUtil;
import com.kangyonggan.app.dfjz.common.PropertiesUtil;
import com.kangyonggan.app.dfjz.common.XmlUtil;
import com.kangyonggan.app.dfjz.model.annotation.LogTime;
import com.kangyonggan.app.dfjz.model.constants.Dialect;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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

    @Override
    public String compareProperties(InputStream inLeft, InputStream inRight) throws Exception {
        Properties propLeft = PropertiesUtil.readProperties(inLeft);
        Properties propRight = PropertiesUtil.readProperties(inRight);

        // 文件1中有但是文件2中没有的key
        List<String> leftHasOnly = compare(propLeft, propRight);

        // 文件2中有但是文件1中没有的key
        List<String> rightHasOnly = compare(propRight, propLeft);

        // 两个文件中都有，但是值不一样的key
        List<String> diff = diff(propLeft, propRight);

        StringBuilder result = new StringBuilder();
        if (!leftHasOnly.isEmpty()) {
            result.append("================================================================\n");
            result.append("文件一独有的key：\n");
            for (String key : leftHasOnly) {
                result.append(key).append("\n");
            }
            result.append("================================================================\n\n\n\n");
        }

        if (!rightHasOnly.isEmpty()) {
            result.append("================================================================\n");
            result.append("文件二独有的key：\n");
            for (String key : rightHasOnly) {
                result.append(key).append("\n");
            }
            result.append("================================================================\n\n\n\n");
        }

        if (!diff.isEmpty()) {
            result.append("================================================================\n");
            result.append("key一样但是值不一样的：\n");
            for (String key : diff) {
                result.append(key).append("\n");
            }
            result.append("================================================================\n\n\n\n");
        }

        if (result.length() == 0) {
            result.append("两个文件key和value都是一样的");
        }

        // 格式化输出
        return result.toString();
    }

    @Override
    public String getBaZi(String lunar, int year, int month, int day, int hour) throws Exception {
        String bazi;
        String yinli;
        String yangli;
        if (lunar.equals("0")) {
            bazi = DestinyUtil.getEightWord(year, month, day, hour);
            yinli = LocalDate.of(year, month, day).format(DateTimeFormatter.BASIC_ISO_DATE);
            yangli = CalendarUtil.lunarToSolar(yinli);
        } else {
            bazi = DestinyUtil.getEightWord4Lunar(year, month, day, hour);
            yangli = LocalDate.of(year, month, day).format(DateTimeFormatter.BASIC_ISO_DATE);
            yinli = CalendarUtil.solarToLunar(yangli);
        }
        String wuxing = DestinyUtil.getWuXing(bazi);
        String shengxiao = DestinyUtil.getShengXiao(year);
        String yunshi = DestinyUtil.getYunShi(wuxing, month);

        StringBuilder result = new StringBuilder();
        result.append("阴历出生年月：").append(yinli).append("\n");
        result.append("阳历出生年月：").append(yangli).append("\n");
        result.append("生辰八字：").append(bazi).append("\n");
        result.append("五行：").append(wuxing).append("\n");
        result.append("生肖：").append(shengxiao).append("\n");
        result.append("运势：").append(yunshi).append("\n");

        return result.toString();
    }

    /**
     * 查找key一样，但是值不一样的
     *
     * @param first
     * @param second
     * @return
     */
    private List<String> diff(Properties first, Properties second) {
        List<String> list = new ArrayList();

        Set<Object> keys = first.keySet();
        for (Object keyObj : keys) {
            String key = (String) keyObj;
            boolean flag = false;
            Set<Object> keys2 = second.keySet();
            for (Object keyObj2 : keys2) {
                String key2 = (String) keyObj2;
                if (key.equals(key2)) {
                    if (!first.get(key).equals(second.get(key2))) {
                        // key一样，但是值不一样
                        flag = true;
                    } else {
                        // 值也一样就pass
                        break;
                    }
                }
            }
            if (flag) {
                list.add(key);
            }
        }
        return list;
    }

    /**
     * 查找first中有的，但是second中没有的
     *
     * @param first
     * @param second
     * @return
     */
    private List<String> compare(Properties first, Properties second) {
        List<String> list = new ArrayList();

        Set<Object> keys = first.keySet();
        for (Object keyObj : keys) {
            String key = (String) keyObj;
            boolean exist = false;
            Set<Object> keys2 = second.keySet();
            for (Object keyObj2 : keys2) {
                String key2 = (String) keyObj2;
                if (key.equals(key2)) {
                    exist = true;
                }
            }
            if (!exist) {
                list.add(key);
            }
        }

        return list;
    }
}
