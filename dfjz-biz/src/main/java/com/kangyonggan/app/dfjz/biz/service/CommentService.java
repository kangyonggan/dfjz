package com.kangyonggan.app.dfjz.biz.service;

import com.kangyonggan.app.dfjz.model.dto.CommentCountDto;
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

    /**
     * 保存评论
     *
     * @param comment
     */
    void saveComment(Comment comment);

    /**
     * 查询文章评论量
     *
     * @return
     */
    List<CommentCountDto> findArticlesCommentCount();

    /**
     * 更新评论者ip信息
     *
     * @param id
     * @param ip
     */
    void updateCommitCity(Long id, String ip);
}
