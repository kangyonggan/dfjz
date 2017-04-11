package com.kangyonggan.app.dfjz.web.controller;

import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.biz.service.CommentService;
import com.kangyonggan.app.dfjz.biz.service.RedisService;
import com.kangyonggan.app.dfjz.biz.util.PropertiesUtil;
import com.kangyonggan.app.dfjz.common.IPUtil;
import com.kangyonggan.app.dfjz.common.MarkdownUtil;
import com.kangyonggan.app.dfjz.model.dto.Toc;
import com.kangyonggan.app.dfjz.model.vo.Article;
import com.kangyonggan.app.dfjz.model.vo.Comment;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

        Object flag = redisService.get("article_id_" + id + "_ip_" + ip);
        if (flag == null) {
            articleService.updateArticleVisitCount(id, ip);
            redisService.set("article_id_" + id + "_ip_" + ip, id, 30 * 60);// 30分钟之内同一个ip只能算访问一次
        }

        model.addAttribute("article", article);
        model.addAttribute("provArticle", provArticle);
        model.addAttribute("nextArticle", nextArticle);
        model.addAttribute("comments", comments);
        model.addAttribute("toc", toc);
        model.addAttribute("commentArticles", commentArticles);
        model.addAttribute("visitArticles", visitArticles);
        model.addAttribute("stickArticles", stickArticles);
        return getPathDetail();
    }

    /**
     * 评论
     *
     * @param comment
     * @param request
     * @return
     */
    @RequestMapping(value = "comment", method = RequestMethod.POST)
    public String comment(@ModelAttribute("comment") @Valid Comment comment, HttpServletRequest request) {
        articleService.updateArticleCommentCount(comment, IPUtil.getIp(request));
        return "redirect:/article/" + comment.getArticleId();
    }

    /**
     * 保存文章
     *
     * @param article
     * @param request
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("article") @Valid Article article, HttpServletRequest request) {
        String ip = IPUtil.getIp(request);
        log.info("发布文章的ip实际地址为：{}", ip);
        String ipWhite = PropertiesUtil.getProperties("article.publish.ip.white");
        log.info("发布文章的ip白名单为：{}", ipWhite);

        if (ipWhite.indexOf(ip) > -1) {
            log.info("文章发布成功");
        } else {
            log.error("{}不在ip白名单中，企图发布文章，已报警！", ip);
        }

        return "redirect:/index";
    }
}
