package com.kangyonggan.app.dfjz.biz.service;

import com.kangyonggan.app.dfjz.model.vo.Repository;

/**
 * @author kangyonggan
 * @since 7/29/17
 */
public interface RepositoryService {

    /**
     * 根据问题查找答案
     *
     * @param question
     * @return
     */
    Repository findAnswerByQuestion(String question);

    /**
     * 保存答案
     *
     * @param repository
     */
    void saveRepository(Repository repository);

    /**
     * 判断此问题是否存在
     *
     * @param question
     * @return
     */
    boolean existQuestion(String question);

    /**
     * 问题的权重加1
     *
     * @param repository
     */
    void updateRepositoryWeight(Repository repository);
}
