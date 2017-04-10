package com.kangyonggan.app.dfjz.biz.service;

import com.kangyonggan.app.dfjz.model.dto.ArticleCountDto;
import com.kangyonggan.app.dfjz.model.dto.Toc;
import com.kangyonggan.app.dfjz.model.vo.Article;
import com.kangyonggan.app.dfjz.model.vo.Comment;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 查找归档文章
     *
     * @param pageNum
     * @return
     */
    List<Article> findArticles4Archives(int pageNum);

    /**
     * 搜索
     *
     * @param question
     * @param pageNum
     * @return
     */
    List<Article> searchArticles(String question, int pageNum);

    /**
     * 更新浏览量
     *
     * @param articleId
     * @param ip
     */
    void updateArticleVisitCount(Long articleId, String ip);

    /**
     * 更新评论数
     *
     * @param comment
     * @param ip
     */
    void updateArticleCommentCount(Comment comment, String ip);

    /**
     * 生成博客的rss
     */
    void genBlogRss();

    /**
     * 生成网站地图
     */
    void genSiteMap();

    /**
     * 查找各个栏目文章数量
     *
     * @return
     */
    List<ArticleCountDto> findArticleCountDto();

}
