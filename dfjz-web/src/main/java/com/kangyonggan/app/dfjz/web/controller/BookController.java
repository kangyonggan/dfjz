package com.kangyonggan.app.dfjz.web.controller;

import com.kangyonggan.app.dfjz.biz.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangyonggan
 * @since 4/13/17
 */
@Controller
@RequestMapping("book")
public class BookController extends BaseController {

    @Autowired
    private BookService bookService;

    /**
     * 生成书籍rss
     *
     * @param id
     * @param pageNum
     * @param startNum
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}", method = RequestMethod.GET)
    @ResponseBody
    public String genRss(@PathVariable("id") Long id,
                         @RequestParam(value = "p", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "s", required = false, defaultValue = "0") int startNum) {
        bookService.genBookRssByPage(id, pageNum, startNum);

        return "ok";
    }

    /**
     * 生成书籍农门悍女rss
     *
     * @return
     */
    @RequestMapping(value = "nmhn", method = RequestMethod.GET)
    @ResponseBody
    public String genNmhnRss() {
        for (int i = 1; i <= 9; i++) {
            bookService.genNMHNRss(i);
        }

        return "ok";
    }

}
