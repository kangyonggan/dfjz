package com.kangyonggan.app.dfjz.web.controller;

import com.kangyonggan.app.dfjz.biz.service.ArticleService;
import com.kangyonggan.app.dfjz.biz.task.ArticleVisitCountTask;
import com.kangyonggan.app.dfjz.biz.task.CategoryArticleCountTask;
import com.kangyonggan.app.dfjz.biz.task.RssTask;
import com.kangyonggan.app.dfjz.biz.task.SiteMapTask;
import com.kangyonggan.app.dfjz.biz.util.PropertiesUtil;
import com.kangyonggan.app.dfjz.common.IPUtil;
import com.kangyonggan.app.dfjz.model.vo.Article;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author kangyonggan
 * @since 2017/4/11 0011
 */
@Controller
@RequestMapping("help")
@Log4j2
public class HelpController {

    @Autowired
    private RssTask rssTask;

    @Autowired
    private CategoryArticleCountTask categoryArticleCountTask;

    @Autowired
    private SiteMapTask siteMapTask;

    @Autowired
    private ArticleVisitCountTask articleVisitCountTask;

    @Autowired
    private ArticleService articleService;

    /**
     * 保存文章
     *
     * @param article
     * @param request
     * @return
     */
    @RequestMapping(value = "article/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("article") @Valid Article article, HttpServletRequest request) {
        String ip = IPUtil.getIp(request);
        log.info("发布文章的ip实际地址为：{}", ip);
        String articlePublishIp = PropertiesUtil.getProperties("article.publish.ip");
        log.info("发布文章的ip白名单为：{}", articlePublishIp);

        if (isContainsIp(ip, articlePublishIp)) {
            articleService.saveArticle(article);
            refreshTask(request);
            log.info("文章发布成功");
        } else {
            log.error("{}不在ip白名单中，企图发布文章，已报警！", ip);
        }

        return "redirect:/#index";
    }

    /**
     * 手动刷新所有任务
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "task/refresh", method = RequestMethod.GET)
    @ResponseBody
    public String refreshTask(HttpServletRequest request) {
        String ip = IPUtil.getIp(request);
        log.info("手动刷新所有任务的ip实际地址为：{}", ip);
        String taskRefreshIp = PropertiesUtil.getProperties("task.refresh.ip");
        log.info("手动刷新所有任务的ip白名单为：{}", taskRefreshIp);

        if (isContainsIp(ip, taskRefreshIp)) {
            categoryArticleCountTask.execute();
            rssTask.execute();
            siteMapTask.execute();
            articleVisitCountTask.execute();
            log.info("手动刷新所有任务成功");
        } else {
            log.error("{}不在ip白名单中，企图手动刷新所有任务，已报警！", ip);
        }
        return "ok";
    }

    /**
     * 手动刷新配置
     *
     * @return
     */
    @RequestMapping(value = "config/refresh", method = RequestMethod.GET)
    @ResponseBody
    public String refreshConfig() {
        PropertiesUtil.refresh();
        return "ok";
    }

    private boolean isContainsIp(String ip, String ips) {
        if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(ips)) {
            return false;
        }

        String ipArr[] = ips.split(",");

        for (String i : ipArr) {
            if (ip.equals(i)) {
                return true;
            }
        }

        return false;
    }

}
