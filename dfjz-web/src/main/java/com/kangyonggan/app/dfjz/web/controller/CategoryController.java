package com.kangyonggan.app.dfjz.web.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.biz.service.CategoryService;
import com.kangyonggan.app.dfjz.model.vo.Article;
import com.kangyonggan.app.dfjz.model.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author kangyonggan
 * @since 4/10/17
 */
@Controller
@RequestMapping("category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    /**
     * 所有分类
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<Category> categories = categoryService.findAllCategories();

        model.addAttribute("categories", categories);
        return getPathIndex();
    }

    /**
     * 某个分类
     *
     * @return
     */
    @RequestMapping(value = "{code:[\\w]+}", method = RequestMethod.GET)
    public String list(@PathVariable("code") String code,
                       @RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                       Model model) {
        Category category = categoryService.findCategoryByCode(code);
        List<Article> articles = articleService.findArticlesByCategory(code, pageNum);
        PageInfo<Article> page = new PageInfo(articles);

        model.addAttribute("category", category);
        model.addAttribute("page", page);
        return getPathList();
    }

}
