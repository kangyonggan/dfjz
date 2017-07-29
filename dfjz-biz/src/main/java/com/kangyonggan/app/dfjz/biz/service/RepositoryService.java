package com.kangyonggan.app.dfjz.biz.service;

import com.kangyonggan.app.dfjz.model.vo.Repository;

import java.util.List;

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

}
