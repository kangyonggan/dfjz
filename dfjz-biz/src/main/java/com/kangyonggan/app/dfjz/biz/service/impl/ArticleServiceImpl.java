package com.kangyonggan.app.dfjz.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.biz.service.CategoryService;
import com.kangyonggan.app.dfjz.biz.service.CommentService;
import com.kangyonggan.app.dfjz.biz.util.PropertiesUtil;
import com.kangyonggan.app.dfjz.common.DateUtil;
import com.kangyonggan.app.dfjz.common.MarkdownUtil;
import com.kangyonggan.app.dfjz.common.StringUtil;
import com.kangyonggan.app.dfjz.mapper.ArticleMapper;
import com.kangyonggan.app.dfjz.model.annotation.LogTime;
import com.kangyonggan.app.dfjz.model.constants.AppConstants;
import com.kangyonggan.app.dfjz.model.dto.ArticleCountDto;
import com.kangyonggan.app.dfjz.model.dto.CommentCountDto;
import com.kangyonggan.app.dfjz.model.dto.Toc;
import com.kangyonggan.app.dfjz.model.dto.VisitCountDto;
import com.kangyonggan.app.dfjz.model.vo.Article;
import com.kangyonggan.app.dfjz.model.vo.Category;
import com.kangyonggan.app.dfjz.model.vo.Comment;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/4/9 0009
 */
@Service
@Log4j2
public class ArticleServiceImpl extends BaseService<Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CategoryService categoryService;

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
    public void updateArticleVisitCount(Long articleId) {
        articleMapper.updateArticleVisitCount(articleId);
    }

    @Override
    @LogTime
    public Long updateArticleCommentCount(Comment comment, String ip) {
        if (StringUtils.isEmpty(comment.getUsername())) {
            comment.setUsername("路人甲");
        }

        if (StringUtils.isEmpty(comment.getEmail())) {
            comment.setEmail("lurenjia@gmail.com");
        }

        if (StringUtils.isEmpty(comment.getContent())) {
            comment.setContent("看完博主的帖子，我的心情竟是久久不能平复，正如老子所云：大音希声，大象希形。我现在终于明白我缺乏的是什么了，正是博主那种对真理的执着追求和博主那种对理想的艰苦实践所产生的厚重感。面对博主的文章，我震惊得几乎不能动弹了，博主那种裂纸欲出的大手笔，竟使我忍不住一次次的翻开博主的帖子，每看一次，赞赏之情就激长数分，我总在想，是否有神灵活在它灵秀的外表下，以至能使人三月不知肉味，使人有余音穿梁，三日不绝的感受。博主，你写得实在是太好了。");
        }


        comment.setIp(ip);
        commentService.saveComment(comment);

        articleMapper.updateArticleCommentCount(comment.getArticleId());

        return comment.getId();
    }

    @Override
    public void genBlogRss() {
        Example example = new Example(Article.class);
        example.createCriteria().andEqualTo("isDeleted", AppConstants.IS_DELETED_NO);
        example.setOrderByClause("id desc");

        genRssFile(super.selectByExample(example));
    }

    @Override
    public void genSiteMap() {
        Example example = new Example(Article.class);
        example.createCriteria().andEqualTo("isDeleted", AppConstants.IS_DELETED_NO);
        example.setOrderByClause("id desc");

        genSiteMap(super.selectByExample(example));
    }

    @Override
    @LogTime
    public List<ArticleCountDto> findArticleCountDto() {
        return articleMapper.selectArticleCountDto();
    }

    @Override
    @LogTime
    public void saveArticle(Article article) {
        Category category = categoryService.findCategoryByCode(article.getCategoryCode());
        article.setCategoryName(category.getName());

        super.insertSelective(article);
    }

    @Override
    @LogTime
    public void updateArticlesVisitCount(List<VisitCountDto> visitCountDtos) {
        Article article;
        for (VisitCountDto dto : visitCountDtos) {
            article = new Article();
            article.setId(dto.getArticleId());
            article.setVisitCount(dto.getVisitCount());
            super.updateByPrimaryKeySelective(article);
        }
    }

    @Override
    @LogTime
    public void updateArticlesCommentCount(List<CommentCountDto> commentCountDtos) {
        Article article;
        for (CommentCountDto dto : commentCountDtos) {
            article = new Article();
            article.setId(dto.getArticleId());
            article.setCommentCount(dto.getCommentCount());
            super.updateByPrimaryKeySelective(article);
        }
    }

    @Override
    @LogTime
    public void updateArticle(Article article) {
        super.updateByPrimaryKeySelective(article);
    }

    private void genSiteMap(List<Article> articles) {
        StringBuilder rss = new StringBuilder("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");

        for (Article article : articles) {
            rss.append("<url>");
            rss.append("<loc>https://www.kangyonggan.com/#article/").append(article.getId()).append("</loc>");
            rss.append("<lastmod>").append(DateUtil.toXmlDateTime(article.getUpdatedTime())).append("</lastmod>");

            rss.append("<data>");
            rss.append("<display>");
            rss.append("<title>").append(article.getTitle()).append("</title>");
            rss.append("<pubTime>").append(DateUtil.toXmlDateTime(article.getCreatedTime())).append("</pubTime>");
            rss.append("<tag>").append(article.getCategoryCode()).append("</tag>");
            rss.append("<tag>").append(article.getCategoryName()).append("</tag>");
            rss.append("<breadCrumb title=\"").append(article.getCategoryName()).append("\" url=\"").append("https://www.kangyonggan.com/#category/").append(article.getCategoryCode()).append("\"/>");
            rss.append("</display>");
            rss.append("</data>");
            rss.append("</url>");
        }

        rss.append("</urlset>");

        File file = new File(PropertiesUtil.getProperties("file.root.path") + "upload/rss/sitemap.xml");

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(rss.toString());
            writer.flush();
        } catch (Exception e) {
            log.error("生成SiteMap异常, 文件路径：" + PropertiesUtil.getProperties("file.root.path") + "upload/rss/sitemap.xml", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    writer = null;
                } catch (Exception e) {
                    log.error("写sitemap后关闭输入流异常", e);
                }
            }
        }
    }

    private void genRssFile(List<Article> articles) {
        StringBuilder rss = new StringBuilder("<feed xmlns=\"http://www.w3.org/2005/Atom\"><title>");
        rss.append(PropertiesUtil.getProperties("app.name")).append("</title>");
        rss.append("<link href=\"/upload/rss/blog.xml\" rel=\"self\"/>").append("<link href=\"https://www.kangyonggan.com/\"/>");
        rss.append("<updated>").append(DateUtil.toXmlDateTime(new Date())).append("</updated>");
        rss.append("<id>https://www.kangyonggan.com/</id>");
        rss.append("<author><name>").append(PropertiesUtil.getProperties("app.author")).append("</name></author>");

        for (Article article : articles) {
            rss.append("<entry><title>").append(article.getTitle()).append("</title>");
            rss.append("<link href=\"https://www.kangyonggan.com/#article/").append(article.getId()).append("\"/>");
            rss.append("<id>https://www.kangyonggan.com/#article/").append(article.getId()).append("</id>");
            rss.append("<published>").append(DateUtil.toXmlDateTime(article.getCreatedTime())).append("</published>");
            rss.append("<updated>").append(DateUtil.toXmlDateTime(article.getUpdatedTime())).append("</updated>");
            rss.append("<content type=\"html\"><![CDATA[").append(MarkdownUtil.markdownToHtml(article.getContent())).append("]]></content>");

            int index = article.getContent().indexOf("<!-- more -->");
            if (index > -1) {
                String summary = article.getContent().substring(0, index);
                rss.append("<summary type=\"html\"><![CDATA[").append(MarkdownUtil.markdownToHtml(summary)).append("]]></summary>");
            } else {
                rss.append("<summary type=\"html\"><![CDATA[").append(MarkdownUtil.markdownToHtml(article.getContent())).append("]]></summary>");
            }

            rss.append("<category term=\"").append(article.getCategoryName()).append("\" scheme=\"https://www.kangyonggan.com/#category/").append(article.getCategoryCode()).append("/\"/>");
            rss.append("</entry>");
        }

        rss.append("</feed>");

        File file = new File(PropertiesUtil.getProperties("file.root.path") + "upload/rss/blog.xml");

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(rss.toString());
            writer.flush();
        } catch (Exception e) {
            log.error("生成博客rss异常, 文件路径：" + PropertiesUtil.getProperties("file.root.path") + "upload/rss/blog.xml", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    writer = null;
                } catch (Exception e) {
                    log.error("写rss后关闭输入流异常", e);
                }
            }
        }
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
            int index = content.indexOf("<!-- more -->");
            if (index > -1) {
                article.setContent(MarkdownUtil.markdownToHtml(content.substring(0, index)));
            } else {
                article.setContent(MarkdownUtil.markdownToHtml(content));
            }
        }
    }
}
