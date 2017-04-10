package com.kangyonggan.app.dfjz.biz.service;

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

}
