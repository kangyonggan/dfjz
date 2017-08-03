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
     * @param startNum
     */
    void genBookRssByPage(Long id, int startNum);

    /**
     * 生成农门悍女
     */
    void genNMHNRss();

}
