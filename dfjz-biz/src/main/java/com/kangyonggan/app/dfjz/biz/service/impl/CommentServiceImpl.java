package com.kangyonggan.app.dfjz.biz.service.impl;

import com.kangyonggan.app.dfjz.biz.service.CommentService;
import com.kangyonggan.app.dfjz.model.annotation.LogTime;
import com.kangyonggan.app.dfjz.model.constants.AppConstants;
import com.kangyonggan.app.dfjz.model.vo.Comment;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/4/9 0009
 */
@Service
public class CommentServiceImpl extends BaseService<Comment> implements CommentService {

    @Override
    @LogTime
    public List<Comment> findCommentsByArticleId(Long articleId) {
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("isDeleted", AppConstants.IS_DELETED_NO).andEqualTo("articleId", articleId);

        example.setOrderByClause("id desc");
        return super.selectByExample(example);
    }

    @Override
    @LogTime
    public void saveComment(Comment comment) {
        super.insertSelective(comment);
    }
}
