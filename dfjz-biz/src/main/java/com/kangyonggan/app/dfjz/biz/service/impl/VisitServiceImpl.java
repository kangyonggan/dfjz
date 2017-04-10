package com.kangyonggan.app.dfjz.biz.service.impl;

import com.kangyonggan.app.dfjz.biz.service.VisitService;
import com.kangyonggan.app.dfjz.common.IPUtil;
import com.kangyonggan.app.dfjz.mapper.VisitMapper;
import com.kangyonggan.app.dfjz.model.annotation.LogTime;
import com.kangyonggan.app.dfjz.model.vo.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author kangyonggan
 * @since 4/10/17
 */
@Service
public class VisitServiceImpl extends BaseService<Visit> implements VisitService {

    @Autowired
    private VisitMapper visitMapper;

    @Override
    @LogTime
    public void saveVisit(Long articleId, String ip) {
        new Thread() {
            public void run() {
                Visit visit = new Visit();

                visit.setArticleId(articleId);
                visit.setIp(ip);
                Map<String, String> resultMap = IPUtil.getIpInfo(ip);
                visit.setCode(resultMap.get("code"));

                if ("0".equals(resultMap.get("code"))) {
                    visit.setArea(resultMap.get("area"));
                    visit.setCity(resultMap.get("city"));
                    visit.setCountry(resultMap.get("country"));
                    visit.setIsp(resultMap.get("isp"));
                    visit.setRegion(resultMap.get("region"));
                    visit.setMsg("查询成功");
                } else {
                    visit.setMsg(resultMap.get("msg"));
                }

                visitMapper.insertSelective(visit);
            }
        }.start();

    }
}