package com.kangyonggan.app.dfjz.biz.task;

import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.biz.service.VisitService;
import com.kangyonggan.app.dfjz.model.dto.VisitCountDto;
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
public class ArticleVisitCountTask {

    @Autowired
    private VisitService visitService;

    @Autowired
    private ArticleService articleService;

    /**
     * 每45分钟执行一次
     * cron表达式：* * * * * *（秒 分 时 日 月 星期）
     */
    @Scheduled(cron = "0 0/45 * * * *")
    public void execute() {
        log.info("定时任务统计文章访问量开始...");
        List<VisitCountDto> visitCountDtos = visitService.findArticlesVisitCount();

        articleService.updateArticlesVisitCount(visitCountDtos);
        log.info("定时任务统计文章访问量结束!");
    }
}
