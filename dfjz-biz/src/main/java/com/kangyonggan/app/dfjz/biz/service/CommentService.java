package com.kangyonggan.app.dfjz.biz.service;

import com.kangyonggan.app.dfjz.model.vo.Comment;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/4/9 0009
 */
public interface CommentService {

    /**
     * 查找文章的所有评论
     *
     * @param articleId
     * @return
     */
    List<Comment> findCommentsByArticleId(Long articleId);

}
