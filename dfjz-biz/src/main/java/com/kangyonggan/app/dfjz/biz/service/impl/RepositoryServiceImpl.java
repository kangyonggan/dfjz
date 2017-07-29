package com.kangyonggan.app.dfjz.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.dfjz.biz.service.RepositoryService;
import com.kangyonggan.app.dfjz.common.StringUtil;
import com.kangyonggan.app.dfjz.model.vo.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author kangyonggan
 * @since 7/29/17
 */
@Service
public class RepositoryServiceImpl extends BaseService<Repository> implements RepositoryService {

    @Override
    public Repository findAnswerByQuestion(String question) {
        Example example = new Example(Repository.class);
        example.createCriteria().andLike("question", StringUtil.toLikeString(question));
        example.setOrderByClause("weight desc");

        PageHelper.offsetPage(1, 1);
        List<Repository> repositories = super.selectByExample(example);
        if (repositories.isEmpty()) {
            return null;
        }

        return repositories.get(0);
    }
}
