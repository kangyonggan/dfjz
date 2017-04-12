package com.kangyonggan.app.dfjz.mapper;

import com.kangyonggan.app.dfjz.model.dto.CommentCountDto;
import com.kangyonggan.app.dfjz.model.vo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends MyMapper<Comment> {

    /**
     * 查询文章评论量
     *
     * @return
     */
    List<CommentCountDto> selectArticlesCommentCount();
}