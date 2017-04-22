package com.kangyonggan.app.dfjz.web.controller;

import com.kangyonggan.app.dfjz.biz.service.DictionaryService;
import com.kangyonggan.app.dfjz.model.vo.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/4/21 0021
 */
@Controller
@RequestMapping("tools")
public class ToolsController extends BaseController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 工具列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return getPathIndex();
    }

    /**
     * ASCLL码对照表
     *
     * @return
     */
    @RequestMapping(value = "ascll", method = RequestMethod.GET)
    public String ascll(Model model) {
        List<Dictionary> asclls = dictionaryService.findDictionariesByType("ASCLL");

        model.addAttribute("asclls", asclls);
        return getPathRoot() + "/ascll";
    }
}
