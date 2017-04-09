package com.kangyonggan.app.dfjz.web.controller;

import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.biz.service.CommentService;
import com.kangyonggan.app.dfjz.common.MarkdownUtil;
import com.kangyonggan.app.dfjz.model.dto.Toc;
import com.kangyonggan.app.dfjz.model.vo.Article;
import com.kangyonggan.app.dfjz.model.vo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/4/8 0008
 */
@Controller
@RequestMapping("article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "{id:[\\d]+}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findArticleById(id);
        Toc toc = articleService.extraArticleToc(article.getContent());

        article.setContent(MarkdownUtil.markdownToHtml(article.getContent()));

        Article provArticle = articleService.findPrevArticle(id);
        Article nextArticle = articleService.findNextArticle(id);

        List<Comment> comments = commentService.findCommentsByArticleId(id);

        model.addAttribute("article", article);
        model.addAttribute("provArticle", provArticle);
        model.addAttribute("nextArticle", nextArticle);
        model.addAttribute("comments", comments);
        model.addAttribute("toc", toc);
        return getPathDetail();
    }
}
