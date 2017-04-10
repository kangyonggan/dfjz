package com.kangyonggan.app.dfjz.biz.service;

import com.kangyonggan.app.dfjz.model.dto.Toc;
import com.kangyonggan.app.dfjz.model.vo.Article;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/4/9 0009
 */
public interface ArticleService {

    /**
     * 分页查找文章，主要用于首页的分页
     *
     * @param pageNum
     * @return
     */
    List<Article> findArticlesByPage(int pageNum);

    /**
     * 查找文章详情
     *
     * @param id
     * @return
     */
    Article findArticleById(Long id);

    /**
     * 上一篇文章
     *
     * @param id
     * @return
     */
    Article findPrevArticle(Long id);

    /**
     * 下一篇文章
     *
     * @param id
     * @return
     */
    Article findNextArticle(Long id);

    /**
     * 提取文章的目录,思路：一行一行处理
     *
     * @param content
     * @return
     */
    Toc extraArticleToc(String content);

    /**
     * 评论排行榜
     *
     * @return
     */
    List<Article> findArticlesOrderByComment();

    /**
     * 阅读排行榜
     *
     * @return
     */
    List<Article> findArticlesOrderByVisit();

    /**
     * 推荐排行榜
     *
     * @return
     */
    List<Article> findArticlesOrderByStick();

    /**
     * 查找某栏目的文章
     *
     * @param categoryCode
     * @param pageNum
     * @return
     */
    List<Article> findArticlesByCategory(String categoryCode, int pageNum);
}
