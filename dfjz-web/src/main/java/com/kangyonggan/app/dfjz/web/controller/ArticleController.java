package com.kangyonggan.app.dfjz.web.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.biz.service.CommentService;
import com.kangyonggan.app.dfjz.biz.service.RedisService;
import com.kangyonggan.app.dfjz.biz.service.VisitService;
import com.kangyonggan.app.dfjz.biz.util.PropertiesUtil;
import com.kangyonggan.app.dfjz.common.IPUtil;
import com.kangyonggan.app.dfjz.common.MarkdownUtil;
import com.kangyonggan.app.dfjz.model.dto.Toc;
import com.kangyonggan.app.dfjz.model.vo.Article;
import com.kangyonggan.app.dfjz.model.vo.Comment;
import com.kangyonggan.app.dfjz.model.vo.Visit;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/4/8 0008
 */
@Controller
@RequestMapping("article")
@Log4j2
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private VisitService visitService;

    /**
     * redis键的前缀
     */
    private String prefix = PropertiesUtil.getProperties("redis.prefix") + ":";

    private String lockDetail = "lockDetail";
    private String lockComment = "lockComment";

    /**
     * 文章详情
     *
     * @param id
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
        getDetail(id, request, model);
        return getPathDetail();
    }

    private void getDetail(@PathVariable("id") final Long id, HttpServletRequest request, Model model) {
        Article article = articleService.findArticleById(id);
        Toc toc = articleService.extraArticleToc(article.getContent());

        article.setContent(MarkdownUtil.markdownToHtml(article.getContent()));

        Article provArticle = articleService.findPrevArticle(id);
        Article nextArticle = articleService.findNextArticle(id);

        List<Comment> comments = commentService.findCommentsByArticleId(id);

        List<Article> commentArticles = articleService.findArticlesOrderByComment();
        List<Article> visitArticles = articleService.findArticlesOrderByVisit();
        List<Article> stickArticles = articleService.findArticlesOrderByStick();

        // 访问量控制
        String ip = IPUtil.getIp(request);

        synchronized (lockDetail) {
            Object flag = redisService.get(prefix + "article_visit_id_" + id + "_ip_" + ip);
            if (flag == null) {
                articleService.updateArticleVisitCount(id);

                redisService.set(prefix + "article_visit_id_" + id + "_ip_" + ip, id, 30 * 60);// 30分钟之内同一个ip只能算访问一次
                log.info("启动线程异步保存访问者ip信息");
                new Thread() {
                    public void run() {
                        visitService.saveVisit(id, ip);
                        log.info("保存访问者ip成功");
                    }
                }.start();
            }
        }

        String articleToken = System.currentTimeMillis() + "";
        log.info("访问文章{}, token={}", id, articleToken);
        request.getSession().setAttribute("articleToken", articleToken);

        model.addAttribute("article", article);
        model.addAttribute("provArticle", provArticle);
        model.addAttribute("nextArticle", nextArticle);
        model.addAttribute("comments", comments);
        model.addAttribute("toc", toc);
        model.addAttribute("commentArticles", commentArticles);
        model.addAttribute("visitArticles", visitArticles);
        model.addAttribute("stickArticles", stickArticles);
    }

    /**
     * 评论
     *
     * @param comment
     * @param articleToken
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "comment", method = RequestMethod.POST)
    public String comment(@ModelAttribute("comment") @Valid Comment comment, @RequestParam("articleToken") String articleToken, HttpServletRequest request, Model model) {
        log.info("评论文章{}, token={}, 评论内容:{}", comment.getArticleId(), articleToken, comment);

        Object sessionTokenObj;
        synchronized (lockComment) {
            sessionTokenObj = request.getSession().getAttribute("articleToken");
            request.getSession().removeAttribute("articleToken");
        }

        if (sessionTokenObj == null) {
            return getPathRoot() + "/warn";
        }

        String sessionToken = (String) sessionTokenObj;
        if (!articleToken.equals(sessionToken)) {
            return getPathRoot() + "/warn";
        }

        // 访问量控制
        String ip = IPUtil.getIp(request);
        Long id = comment.getArticleId();
        Object flag = redisService.get(prefix + "article_comment_id_" + id + "_ip_" + ip);
        if (flag == null) {
            redisService.set(prefix + "article_comment_id_" + id + "_ip_" + ip, id, 3 * 60);// 3分钟之内同一个ip只能算评论一次
            Long commenId = articleService.updateArticleCommentCount(comment, ip);

            log.info("异步查询ip信息，查回后更新");
            new Thread() {
                public void run() {
                    commentService.updateCommitCity(commenId, ip);
                    log.info("更新评论的ip信息成功");
                }
            }.start();
        } else {
            return getPathRoot() + "/warn";
        }

        getDetail(id, request, model);
        return getPathDetail();
    }

    /**
     * 文章的访客
     *
     * @param id
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}/visits", method = RequestMethod.GET)
    public String visits(@PathVariable("id") Long id,
                         @RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                         Model model) {
        Article article = articleService.findArticleById(id);
        List<Visit> visits = visitService.findVisitsByArticleId(id, pageNum);
        PageInfo<Visit> page = new PageInfo(visits);

        model.addAttribute("page", page);
        model.addAttribute("article", article);
        return getPathRoot() + "/visits";
    }
}
