package com.kangyonggan.app.dfjz.biz.task;

import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author kangyonggan
 * @since 2017/4/10 0010
 */
//@Component
@Log4j2
public class RssTask {

    @Autowired
    private ArticleService articleService;

    /**
     * 每40分钟执行一次
     * cron表达式：* * * * * *（秒 分 时 日 月 星期）
     */
    @Scheduled(cron = "0 0/40 * * * *")
    public void execute() {
        log.info("定时任务自动生成RSS开始...");
//        articleService.genBlogRss();
        log.info("定时任务自动生成RSS结束!");
    }

}
