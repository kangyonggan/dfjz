package com.kangyonggan.app.dfjz.biz.task;

import com.kangyonggan.app.dfjz.biz.service.BookService;
import com.kangyonggan.app.dfjz.biz.util.PropertiesUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author kangyonggan
 * @since 2017/4/10 0010
 */
@Component
@Log4j2
public class BookTask {

    @Autowired
    private BookService bookService;

    /**
     * 每天凌晨6点执行
     * cron表达式：* * * * * *（秒 分 时 日 月 星期）
     */
    @Scheduled(cron = "0 0 6 * * *")
    public void execute() {
        log.info("定时任务自动更新书籍开始...");

        Object opsObj = PropertiesUtil.getProperties("book.update.ops");
        if (opsObj == null) {
            log.info("没有需要更新的书籍");
        }

        String ops = (String) opsObj;
        String opsArr[] = ops.split(",");

        for (String op : opsArr) {
            String info[] = op.split("_");
            try {
                bookService.genBookRssByPage(Long.parseLong(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2]));
            } catch (Exception e) {
                log.error("书籍更新失败:" + op, e);
            }
        }

        log.info("定时任务自动更新书籍结束!");
    }

}
