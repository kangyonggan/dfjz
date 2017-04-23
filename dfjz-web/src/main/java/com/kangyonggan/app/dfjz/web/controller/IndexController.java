package com.kangyonggan.app.dfjz.web.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.biz.service.DictionaryService;
import com.kangyonggan.app.dfjz.biz.util.PropertiesUtil;
import com.kangyonggan.app.dfjz.model.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2017/3/27
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private DictionaryService dictionaryService;

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
     * 订阅
     *
     * @return
     */
    @RequestMapping(value = "rss", method = RequestMethod.GET)
    public String rss(Model model) {
        Map<String, String> rssMap = new HashMap();

        File rssDir = new File(PropertiesUtil.getProperties("file.root.path") + "rss/");
        File files[] = rssDir.listFiles();

        for (File file : files) {
            String filename = file.getName();
            if ("blog.xml".equals(filename) || "sitemap.xml".equals(filename)) {
                continue;
            }

            rssMap.put(filename.substring(0, filename.lastIndexOf(".")), filename);
        }

        model.addAttribute("rssMap", rssMap);
        return getPathRoot() + "/rss";
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

    /**
     * 导航
     * @param model
     * @return
     */
    @RequestMapping(value = "nav", method = RequestMethod.GET)
    public String nav(Model model) {
        model.addAttribute("navs", dictionaryService.findDictionariesByType("NAV"));
        return getPathRoot() + "/nav";
    }

}
