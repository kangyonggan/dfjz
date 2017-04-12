package com.kangyonggan.app.dfjz.biz.task;

import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.biz.service.CommentService;
import com.kangyonggan.app.dfjz.model.dto.CommentCountDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kangyonggan
 * @since 4/12/17
 */
@Component
@Log4j2
public class ArticleCommentCountTask {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    /**
     * 每60分钟执行一次
     * cron表达式：* * * * * *（秒 分 时 日 月 星期）
     */
    @Scheduled(cron = "0 0/60 * * * *")
    public void execute() {
        log.info("定时任务统计文章评论量开始...");
        List<CommentCountDto> commentCountDtos = commentService.findArticlesCommentCount();

        articleService.updateArticlesCommentCount(commentCountDtos);
        log.info("定时任务统计文章评论量结束!");
    }
}
