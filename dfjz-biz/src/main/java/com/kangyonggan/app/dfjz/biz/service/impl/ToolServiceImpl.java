package com.kangyonggan.app.dfjz.biz.service.impl;

import com.kangyonggan.app.dfjz.biz.service.ToolService;
import com.kangyonggan.app.dfjz.common.XmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author kangyonggan
 * @since 2017/4/22 0022
 */
@Service
public class ToolServiceImpl implements ToolService {

    @Override
    public String formatXml(String data) {
        if (StringUtils.isEmpty(data)) {
            return "xml不能为空";
        }

        try {
            return XmlUtil.format(data);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
