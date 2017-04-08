package com.kangyonggan.app.dfjz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author kangyonggan
 * @since 2017/3/27
 */
@Controller
@RequestMapping("/")
public class ErrorController {

    /**
     * 404 not found
     *
     * @return
     */
    @RequestMapping(value = "error/404", method = RequestMethod.GET)
    public String error404() {
        return "redirect:/#404";
    }


}
