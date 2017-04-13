package com.kangyonggan.app.dfjz.biz.book;

import com.kangyonggan.app.dfjz.biz.util.PropertiesUtil;
import com.kangyonggan.app.dfjz.common.DateUtil;
import com.kangyonggan.app.dfjz.common.HtmlUtil;
import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * 《帝凰》
 *
 * @author kangyonggan
 * @since 4/13/17
 */
@Log4j2
public class DiHuang {

    public static void main(String[] args) {
        String rssName = "帝凰";

        StringBuilder rss = new StringBuilder();
        // 把开头信息写入
        rss.append("<feed xmlns=\"http://www.w3.org/2005/Atom\"><title>").append(rssName).append("</title>");
        rss.append("<link href=\"/upload/rss/" + rssName + ".xml\" rel=\"self\"/>").append("<link href=\"http://www.kangyonggan.com/\"/>");
        rss.append("<updated>").append(DateUtil.toXmlDateTime(new Date())).append("</updated>");
        rss.append("<id>http://www.kangyonggan.com/</id>");
        rss.append("<author><name>").append("天下归元").append("</name></author>");


        // 章节列表
        Document bookDoc = HtmlUtil.parseUrl("http://www.kanunu8.com/book/4256/");
        Elements chapterElements = bookDoc.select("body div table").get(8).select("tbody tr").get(3).select("td table").get(1).select("tbody tr td a");
        log.info("{}书籍总共{}个大章节, 现在抓取所有章节", rssName, chapterElements.size());

        for (Element chapter : chapterElements) {
            String url = chapter.attr("href");
            if ("48817.html".equals(url)) {
                break;
            }
            url = "http://www.kanunu8.com/book/4256/" + url;
            Document chapterDoc = HtmlUtil.parseUrl(url);

            String title = chapterDoc.select("body div table").get(3).select("tbody tr td strong font").html().trim();
            String content = chapterDoc.select("body div table").get(4).select("tbody tr td p").html().trim();

            rss.append("<entry>");
            rss.append("<title>").append(title).append("</title>");
            rss.append("<link href=\"").append(url).append("\"/>");
            rss.append("<id>").append(url).append("</id>");
            rss.append("<published>").append(DateUtil.toXmlDateTime(new Date())).append("</published>");
            rss.append("<updated>").append(DateUtil.toXmlDateTime(new Date())).append("</updated>");
            rss.append("<content type=\"html\"><![CDATA[").append(content).append("]]></content>");
            if (content.length() > 50) {
                rss.append("<summary type=\"html\"><![CDATA[").append(content.substring(0, 50)).append("]]></summary>");
            } else {
                rss.append("<summary type=\"html\"><![CDATA[").append(content).append("]]></summary>");
            }
            rss.append("<category term=\"").append("书籍").append("\" scheme=\"").append("http://www.kanunu8.com/book/4256/").append("/\"/>");
            rss.append("</entry>");

        }

        // 把结尾信息写入
        rss.append("</feed>");

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(PropertiesUtil.getProperties("file.root.path") + "upload/rss/" + rssName + ".xml"));
            writer.write(rss.toString());
            writer.flush();
            log.info("书籍抓取完成");
        } catch (IOException e) {
            log.error("书籍抓取完成后，写入异常", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error("书籍抓取时关闭流异常", e);
                }
            }
        }
    }
}
