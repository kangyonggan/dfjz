package com.kangyonggan.app.dfjz.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.dfjz.biz.service.RepositoryService;
import com.kangyonggan.app.dfjz.common.StringUtil;
import com.kangyonggan.app.dfjz.mapper.RepositoryMapper;
import com.kangyonggan.app.dfjz.model.annotation.LogTime;
import com.kangyonggan.app.dfjz.model.vo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author kangyonggan
 * @since 7/29/17
 */
@Service
public class RepositoryServiceImpl extends BaseService<Repository> implements RepositoryService {

    @Autowired
    private RepositoryMapper repositoryMapper;

    @Override
    @LogTime
    public Repository findAnswerByQuestion(String question) {
        Example example = new Example(Repository.class);
        example.createCriteria().andLike("question", StringUtil.toLikeString(question));
        example.setOrderByClause("weight desc");

        PageHelper.offsetPage(0, 1);
        List<Repository> repositories = super.selectByExample(example);
        if (repositories.isEmpty()) {
            return null;
        }

        return repositories.get(0);
    }

    @Override
    @LogTime
    public void saveRepository(Repository repository) {
        super.insertSelective(repository);
    }

    @Override
    @LogTime
    public boolean existQuestion(String question) {
        Repository repository = new Repository();
        repository.setQuestion(question);

        return repositoryMapper.selectCount(repository) == 1;
    }

    @Override
    @LogTime
    public void updateRepositoryWeight(Repository repository) {
        repositoryMapper.updateRepositoryWeight(repository.getQuestion());
    }
}
