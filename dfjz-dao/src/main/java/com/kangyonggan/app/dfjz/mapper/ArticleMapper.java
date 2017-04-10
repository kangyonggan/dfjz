package com.kangyonggan.app.dfjz.mapper;

import com.kangyonggan.app.dfjz.model.vo.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends MyMapper<Article> {

    /**
     * 查找下一篇文章
     *
     * @param id
     * @return
     */
    Article selectNextArticle(@Param("id") Long id);

    /**
     * 查找上一篇文章
     *
     * @param id
     * @return
     */
    Article selectPrevArticle(@Param("id") Long id);

    /**
     * 评论排行榜
     *
     * @return
     */
    List<Article> selectArticlesOrderByComment();

    /**
     * 阅读排行榜
     *
     * @return
     */
    List<Article> selectArticlesOrderByVisit();

    /**
     * 推荐排行榜
     *
     * @return
     */
    List<Article> selectArticlesOrderByStick();

    /**
     * 查询某栏目的文章
     *
     * @param categoryCode
     * @return
     */
    List<Article> selectArticlesByCategory(@Param("categoryCode") String categoryCode);
}