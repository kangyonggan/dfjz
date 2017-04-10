package com.kangyonggan.app.dfjz.biz.service.impl;

import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.biz.service.CategoryService;
import com.kangyonggan.app.dfjz.mapper.CategoryMapper;
import com.kangyonggan.app.dfjz.model.annotation.LogTime;
import com.kangyonggan.app.dfjz.model.constants.AppConstants;
import com.kangyonggan.app.dfjz.model.dto.ArticleCountDto;
import com.kangyonggan.app.dfjz.model.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author kangyonggan
 * @since 4/10/17
 */
@Service
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleService articleService;

    @Override
    @LogTime
    public List<Category> findAllCategories() {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("isDeleted", AppConstants.IS_DELETED_NO);
        example.setOrderByClause("sort asc");

        return super.selectByExample(example);
    }

    @Override
    @LogTime
    public Category findCategoryByCode(String code) {
        Category category = new Category();
        category.setCode(code);

        return super.selectOne(category);
    }

    @Override
    @LogTime
    public void updateCategoriesArticleCount() {
        List<ArticleCountDto> articleCountDtoList = articleService.findArticleCountDto();

        for (ArticleCountDto dto : articleCountDtoList) {
            updateCategoriesArticleCount(dto);
        }
    }

    private void updateCategoriesArticleCount(ArticleCountDto dto) {
        Category category = new Category();
        category.setArticleCount(dto.getArticleCount());

        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("code", dto.getCategoryCode());
        categoryMapper.updateByExampleSelective(category, example);
    }
}
