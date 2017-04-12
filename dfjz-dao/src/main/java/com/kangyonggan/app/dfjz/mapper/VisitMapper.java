package com.kangyonggan.app.dfjz.mapper;

import com.kangyonggan.app.dfjz.model.dto.VisitCountDto;
import com.kangyonggan.app.dfjz.model.vo.Visit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitMapper extends MyMapper<Visit> {

    /**
     * 查找访问者
     *
     * @return
     */
    List<Visit> findVisits();

    /**
     * 查询文章访问量
     *
     * @return
     */
    List<VisitCountDto> selectArticlesVisitCount();

}