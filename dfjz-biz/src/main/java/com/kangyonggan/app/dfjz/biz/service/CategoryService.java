package com.kangyonggan.app.dfjz.biz.service;

import com.kangyonggan.app.dfjz.model.dto.ArticleCountDto;
import com.kangyonggan.app.dfjz.model.vo.Category;

import java.util.List;

/**
 * @author kangyonggan
 * @since 4/10/17
 */
public interface CategoryService {

    /**
     * 查找所有分类
     *
     * @return
     */
    List<Category> findAllCategories();

    /**
     * 查找分类
     *
     * @param code
     * @return
     */
    Category findCategoryByCode(String code);

    /**
     * 更新栏目文章数量
     */
    void updateCategoriesArticleCount();

}
