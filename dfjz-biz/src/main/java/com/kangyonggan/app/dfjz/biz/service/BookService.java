package com.kangyonggan.app.dfjz.biz.service;

/**
 * @author kangyonggan
 * @since 4/13/17
 */
public interface BookService {

    /**
     * 生成书籍rss订阅源
     *
     * @param id
     * @param pageNum
     */
    void genBookRssByPage(Long id, int pageNum);

}
