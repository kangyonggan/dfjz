package com.kangyonggan.app.dfjz.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.biz.service.VisitService;
import com.kangyonggan.app.dfjz.common.MarkdownUtil;
import com.kangyonggan.app.dfjz.common.StringUtil;
import com.kangyonggan.app.dfjz.mapper.ArticleMapper;
import com.kangyonggan.app.dfjz.model.annotation.LogTime;
import com.kangyonggan.app.dfjz.model.constants.AppConstants;
import com.kangyonggan.app.dfjz.model.dto.Toc;
import com.kangyonggan.app.dfjz.model.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/4/9 0009
 */
@Service
public class ArticleServiceImpl extends BaseService<Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private VisitService visitService;

    @Override
    @LogTime
    public List<Article> findArticlesByPage(int pageNum) {
        Example example = new Example(Article.class);
        example.createCriteria().andEqualTo("isDeleted", AppConstants.IS_DELETED_NO);
        example.setOrderByClause("id desc");

        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        List<Article> articles = super.selectByExample(example);

        processSummary(articles);

        return articles;
    }

    @Override
    @LogTime
    public Article findArticleById(Long id) {
        return super.selectByPrimaryKey(id);
    }

    @Override
    @LogTime
    public Article findPrevArticle(Long id) {
        return articleMapper.selectPrevArticle(id);
    }

    @Override
    @LogTime
    public Article findNextArticle(Long id) {
        return articleMapper.selectNextArticle(id);
    }

    @Override
    @LogTime
    public Toc extraArticleToc(String content) {
        Toc toc = new Toc();

        // 获取所有的行
        String lines[] = content.split("\\n");
        List<Toc> childrens = new ArrayList();
        processLines(childrens, lines, -1, "#");
        toc.setChildrens(childrens);

        return toc;
    }

    @Override
    @LogTime
    public List<Article> findArticlesOrderByComment() {
        return articleMapper.selectArticlesOrderByComment();
    }

    @Override
    @LogTime
    public List<Article> findArticlesOrderByVisit() {
        return articleMapper.selectArticlesOrderByVisit();
    }

    @Override
    @LogTime
    public List<Article> findArticlesOrderByStick() {
        return articleMapper.selectArticlesOrderByStick();
    }

    @Override
    @LogTime
    public List<Article> findArticlesByCategory(String categoryCode, int pageNum) {
        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE * 2);
        return articleMapper.selectArticlesByCategory(categoryCode);
    }

    @Override
    @LogTime
    public List<Article> findArticles4Archives(int pageNum) {
        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE * 2);
        return articleMapper.selectArticles4Archives();
    }

    @Override
    @LogTime
    public List<Article> searchArticles(String question, int pageNum) {
        Example example = new Example(Article.class);
        Example.Criteria criteria1 = example.createCriteria();

        criteria1.andEqualTo("isDeleted", AppConstants.IS_DELETED_NO);
        criteria1.andLike("title", StringUtil.toLikeString(question));

        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andEqualTo("isDeleted", AppConstants.IS_DELETED_NO);
        criteria2.andLike("title", StringUtil.toLikeString(question));

        example.or(criteria2);
        example.setOrderByClause("id desc");

        PageHelper.startPage(pageNum, AppConstants.PAGE_SIZE);
        List<Article> articles = super.selectByExample(example);

        processSummary(articles);

        // 关键字标红(有风险)
        processQueryKey(articles, question);

        return articles;
    }

    @Override
    @LogTime
    public void updateArticleVisitCount(Long articleId, String ip) {
        visitService.saveVisit(articleId, ip);

        articleMapper.updateArticleVisitCount(articleId);
    }

    private void processQueryKey(List<Article> articles, String question) {
        String replacement = "<span class='red'>" + question + "</span>";
        for (Article article : articles) {
            article.setTitle(article.getTitle().replaceAll("(?i)" + question, replacement));
            article.setContent(article.getContent().replaceAll("(?i)" + question, replacement));
        }
    }

    private void processLines(List<Toc> childrens, String lines[], int startLine, String level) {
        if (level.length() == 7) {
            return;
        }

        String nextLevel = level + "#";
        for (int i = startLine + 1; i < lines.length; i++) {
            String line = lines[i];
            if (line.length() > 0 && '#' == line.charAt(0)) {
                int temp = line.indexOf(" ");
                String lineLevel = line.substring(0, temp);

                if (lineLevel.length() <= level.length()) {
                    break;
                }

                if (!nextLevel.equals(lineLevel)) {
                    continue;
                }

                String name = line.substring(temp + 1);

                Toc toc = new Toc();
                toc.setLevel(lineLevel.length());
                toc.setName(name);
                toc.setSort(childrens.size());
                List<Toc> lineChildrens = new ArrayList();
                toc.setChildrens(lineChildrens);
                processLines(lineChildrens, lines, i, lineLevel);

                childrens.add(toc);
            }
        }

        if (childrens.size() == 0) {
            processLines(childrens, lines, startLine, nextLevel);
        }
    }

    /**
     * 处理摘要，就是截取<!-- more -->之前的,顺便把md转为html
     *
     * @param articles
     * @return
     */
    private void processSummary(List<Article> articles) {
        for (Article article : articles) {
            String content = article.getContent();
            String summary = content.substring(0, content.indexOf("<!-- more -->"));
            article.setContent(MarkdownUtil.markdownToHtml(summary));
        }
    }
}
