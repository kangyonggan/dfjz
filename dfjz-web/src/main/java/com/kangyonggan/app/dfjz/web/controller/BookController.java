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
     * @param startNum
     * @return
     */
    @RequestMapping(value = "{id:[\\d]+}", method = RequestMethod.GET)
    @ResponseBody
    public String genRss(@PathVariable("id") Long id,
                         @RequestParam(value = "s", required = false, defaultValue = "0") int startNum) {
        bookService.genBookRssByPage(id, startNum);

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
        bookService.genNMHNRss();

        return "ok";
    }

}
