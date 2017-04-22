package com.kangyonggan.app.dfjz;

import com.kangyonggan.app.dfjz.common.HtmlUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2017/4/22 0022
 */
public class HtmlTest extends AbstractServiceTest {

    @Test
    public void init() {
        Map<String, String> map = new HashMap();
        map.put("\"", "&quot;");
        map.put("&", "&amp;");
        map.put("<", "&lt;");
        map.put(">", "&gt;");
        map.put("空格", "&nbsp;");

        Document doc = HtmlUtil.parseUrl("http://tool.oschina.net/commons?type=2");

        if (doc != null) {
            Elements trs = doc.select("#mainContent .toolUsing .table tbody tr");

            for (int i = 1; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Elements tds = tr.select("td");

                map.put(tds.get(0).text(), tds.get(2).text());
                map.put(tds.get(3).text(), tds.get(5).text());
                if (tds.size() > 7) {
                    map.put(tds.get(6).text(), tds.get(8).text());
                }
            }

            StringBuilder sql = new StringBuilder("INSERT INTO dictionary\n\t(type, code, name, sort)\nVALUES");
            int sort = 0;
            for (String key : map.keySet()) {
                sql.append("\n\t('HTML', '").append(key).append("', '").append(map.get(key)).append("', ").append(sort++).append("),");
            }

            sql.deleteCharAt(sql.lastIndexOf(",")).append(";\n");

            System.out.println(sql);
        }
    }

}
