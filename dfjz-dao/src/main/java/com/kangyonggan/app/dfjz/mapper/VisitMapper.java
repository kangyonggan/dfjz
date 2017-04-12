package com.kangyonggan.app.dfjz.mapper;

import com.kangyonggan.app.dfjz.model.dto.VisitCountDto;
import com.kangyonggan.app.dfjz.model.vo.Visit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitMapper extends MyMapper<Visit> {

    /**
     * 查找所有访问者，带出文章标题
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