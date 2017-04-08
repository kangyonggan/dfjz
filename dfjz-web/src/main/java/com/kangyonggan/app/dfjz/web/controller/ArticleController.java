package com.kangyonggan.app.dfjz.web.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author kangyonggan
 * @since 2017/4/8 0008
 */
@Controller
@RequestMapping("article")
@Log4j2
public class ArticleController extends BaseController {

    @RequestMapping(value = "{id:[\\d]+}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id, Model model) {
        log.info("文章{}的详情", id);
        return getPathDetail();
    }

}
