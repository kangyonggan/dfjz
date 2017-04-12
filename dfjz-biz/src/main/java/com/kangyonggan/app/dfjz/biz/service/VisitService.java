package com.kangyonggan.app.dfjz.biz.service;

import com.kangyonggan.app.dfjz.model.dto.VisitCountDto;
import com.kangyonggan.app.dfjz.model.vo.Visit;

import java.util.List;

/**
 * @author kangyonggan
 * @since 4/10/17
 */
public interface VisitService {

    /**
     * 保存访问者记录
     *
     * @param articleId
     * @param ip
     */
    void saveVisit(Long articleId, String ip);

    /**
     * 分页查找访问量
     *
     * @param pageNum
     * @return
     */
    List<Visit> findVisitsByPage(int pageNum);

    /**
     * 查询文章访问量
     */
    List<VisitCountDto> findArticlesVisitCount();

}
