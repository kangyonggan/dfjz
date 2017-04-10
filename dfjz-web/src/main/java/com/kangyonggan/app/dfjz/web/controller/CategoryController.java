package com.kangyonggan.app.dfjz.web.controller;

import com.kangyonggan.app.dfjz.biz.service.CategoryService;
import com.kangyonggan.app.dfjz.model.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author kangyonggan
 * @since 4/10/17
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分类
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String category(Model model) {
        List<Category> categories = categoryService.findAllCategories();

        model.addAttribute("categories", categories);
        return "category";
    }

}
