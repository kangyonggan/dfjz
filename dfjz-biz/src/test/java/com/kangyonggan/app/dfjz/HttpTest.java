package com.kangyonggan.app.dfjz;

import com.kangyonggan.app.dfjz.biz.service.DictionaryService;
import com.kangyonggan.app.dfjz.common.HtmlUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 2017/4/22 0022
 */
public class HttpTest extends AbstractServiceTest {

    @Test
    public void init() {
        Map<Integer, String> map = new HashMap();
        Document doc = HtmlUtil.parseUrl("http://tool.oschina.net/commons?type=5");

        if (doc != null) {
            Elements trs = doc.select("#mainContent .toolUsing .table tbody tr");

            for (int i = 1; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Elements tds = tr.select("td");

                map.put(Integer.parseInt(tds.get(0).text()), tds.get(1).text());
            }

            StringBuilder sql = new StringBuilder("INSERT INTO dictionary\n\t(type, code, name, sort)\nVALUES");
            for (Integer key : map.keySet()) {
                sql.append("\n\t('HTTP', '").append(key).append("', '").append(map.get(key)).append("', ").append(key).append("),");
            }

            sql.deleteCharAt(sql.lastIndexOf(",")).append(";\n");

            System.out.println(sql);
        }
    }

}
