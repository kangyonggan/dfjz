package com.kangyonggan.app.dfjz.biz.service.impl;

import com.kangyonggan.app.dfjz.biz.service.BookService;
import com.kangyonggan.app.dfjz.biz.service.RedisService;
import com.kangyonggan.app.dfjz.biz.util.PropertiesUtil;
import com.kangyonggan.app.dfjz.common.DateUtil;
import com.kangyonggan.app.dfjz.common.HtmlUtil;
import com.kangyonggan.app.dfjz.model.vo.Book;
import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * @author kangyonggan
 * @since 4/13/17
 */
@Service
@Log4j2
public class BookServiceImpl extends BaseService<Book> implements BookService {

    private static final String BOOK_BASE_URL = "http://www.biquge.cn";

    /**
     * redis键的前缀
     */
    private String prefix = PropertiesUtil.getProperties("redis.prefix") + ":";

    @Autowired
    private RedisService redisService;

    @Override
    public void genBookRssByPage(Long id, int pageNum) {
        // 判断是否重复执行
        if (isExecuting()) {
            log.warn("书籍执行引擎正在执行中, 不可重复执行!");
            return;
        }

        // 执行标致放入redis
        redisService.set(prefix + "bookEngine", "true", 5 * 60);

        Book book = super.selectByPrimaryKey(id);
        if (book == null) {
            log.warn("书籍{}不存在", id);
            redisService.delete(prefix + "bookEngine");
            return;
        }

        // 抓取第pageNum页
        Document bookDoc = HtmlUtil.parseUrl(BOOK_BASE_URL + "/book/" + book.getUrl());
        if (bookDoc == null) {
            log.info("抓取书籍{}章节列表为空，结束抓取!", book.getName());
            redisService.delete(prefix + "bookEngine");
            log.info("清除书籍引擎执行标致成功");
            return;
        }

        int start = (pageNum - 1) * 100 + 1;
        int end = (pageNum - 1) * 100 + 100;

        String rssName = book.getName() + "(" + start + "章-" + end + "章)";

        // 章节列表
        Elements chapterElements = bookDoc.select("#list dl dd a");
        log.info("{}书籍总共{}章节, 现在抓取{}-{}章节", book.getName(), chapterElements.size(), start, end);

        StringBuilder rss = new StringBuilder();
        // 把开头信息写入
        rss.append("<feed xmlns=\"http://www.w3.org/2005/Atom\"><title>").append(rssName).append("</title>");
        rss.append("<link href=\"/upload/rss/" + rssName + ".xml\" rel=\"self\"/>").append("<link href=\"http://www.kangyonggan.com/\"/>");
        rss.append("<updated>").append(DateUtil.toXmlDateTime(new Date())).append("</updated>");
        rss.append("<id>http://www.kangyonggan.com/</id>");
        rss.append("<author><name>").append(book.getAuthor()).append("</name></author>");

        // 写入章节信息
        for (int i = start - 1; i < end; i++) {
            Element chapterElement = chapterElements.get(i);
            processChapter(rss, book, chapterElement);
        }

        // 把结尾信息写入
        rss.append("</feed>");

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(PropertiesUtil.getProperties("file.root.path") + "upload/rss/" + rssName + ".xml"));
            writer.write(rss.toString());
            writer.flush();
        } catch (IOException e) {
            log.error("书籍抓取时异常", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error("书籍抓取时关闭流异常", e);
                }
            }

            redisService.delete(prefix + "bookEngine");
        }
    }

    /**
     * 抓取书籍的某个章节
     *
     * @param rss
     * @param book
     * @param chapter
     */
    private void processChapter(StringBuilder rss, Book book, Element chapter) {
        String chapterUrl = chapter.attr("href");
        Document chapterDoc = HtmlUtil.parseUrl(BOOK_BASE_URL + chapterUrl);

        if (chapterDoc == null) {
            log.error("{}书籍的章节{}抓取失败", book.getName(), chapterUrl);
            return;
        }

        String title = chapterDoc.select(".bookname h1").html().trim();
        String content = chapterDoc.getElementById("content").html();

        rss.append("<entry>");
        rss.append("<title>").append(title).append("</title>");
        rss.append("<link href=\"").append(BOOK_BASE_URL + chapterUrl).append("\"/>");
        rss.append("<link href=\"").append(BOOK_BASE_URL + chapterUrl).append("\"/>");
        rss.append("<id>").append(BOOK_BASE_URL + chapterUrl).append("</id>");
        rss.append("<published>").append(DateUtil.toXmlDateTime(new Date())).append("</published>");
        rss.append("<updated>").append(DateUtil.toXmlDateTime(new Date())).append("</updated>");
        rss.append("<content type=\"html\"><![CDATA[").append(content).append("]]></content>");
        if (content.length() > 50) {
            rss.append("<summary type=\"html\"><![CDATA[").append(content.substring(0, 50)).append("]]></summary>");
        } else {
            rss.append("<summary type=\"html\"><![CDATA[").append(content).append("]]></summary>");
        }
        rss.append("<category term=\"").append("书籍").append("\" scheme=\"").append(BOOK_BASE_URL + "/book/" + book.getUrl()).append("/\"/>");
        rss.append("</entry>");

        log.error("{}书籍的章节{}抓取完成", book.getName(), chapterUrl);
    }

    /**
     * 判断书籍执行引擎是否正在执行
     *
     * @return
     */
    public boolean isExecuting() {
        Object bookEngineRedis = redisService.get(prefix + "bookEngine");
        if (bookEngineRedis == null) {
            return false;
        }
        return true;
    }
}
