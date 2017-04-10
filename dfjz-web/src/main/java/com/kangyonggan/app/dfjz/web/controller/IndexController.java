package com.kangyonggan.app.dfjz.web.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.model.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/3/27
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String layout() {
        return "layout";
    }

    /**
     * 首页
     *
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                        Model model) {
        List<Article> articles = articleService.findArticlesByPage(pageNum);
        PageInfo<Article> page = new PageInfo(articles);

        model.addAttribute("page", page);
        return getPathIndex();
    }

    /**
     * 关于
     *
     * @return
     */
    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String about() {
        return getPathRoot() + "/about";
    }

    /**
     * 归档
     *
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping(value = "archives", method = RequestMethod.GET)
    public String archives(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                           Model model) {
        List<Article> articles = articleService.findArticles4Archives(pageNum);
        PageInfo<Article> page = new PageInfo(articles);

        model.addAttribute("page", page);
        return getPathRoot() + "/archives";
    }

    /**
     * 订阅
     *
     * @return
     */
    @RequestMapping(value = "rss", method = RequestMethod.GET)
    @ResponseBody
    public String rss() {
        articleService.genBlogRss();
        return "ok";
    }

    /**
     * 搜索
     *
     * @param question
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "q") String question,
                         @RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                         Model model) {
        List<Article> articles = articleService.searchArticles(question, pageNum);
        PageInfo<Article> page = new PageInfo(articles);

        model.addAttribute("page", page);
        return getPathIndex();
    }


}
