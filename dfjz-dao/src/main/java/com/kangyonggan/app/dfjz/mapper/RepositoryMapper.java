package com.kangyonggan.app.dfjz.mapper;

import com.kangyonggan.app.dfjz.model.vo.Repository;
import org.apache.ibatis.annotations.Param;

@org.springframework.stereotype.Repository
public interface RepositoryMapper extends MyMapper<Repository> {

    /**
     * 问题权重加1
     *
     * @param question
     * @param answer
     */
    void updateRepositoryWeight(@Param("question") String question, @Param("answer") String answer);
}