package com.kangyonggan.app.dfjz.web.controller;

import com.github.pagehelper.PageInfo;
import com.kangyonggan.app.dfjz.biz.service.VisitService;
import com.kangyonggan.app.dfjz.model.vo.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author kangyonggan
 * @since 4/12/17
 */
@Controller
@RequestMapping("stat")
public class StatController extends BaseController {

    @Autowired
    private VisitService visitService;

    /**
     * 访问量统计
     *
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(value = "p", required = false, defaultValue = "1") int pageNum, Model model) {
        List<Visit> visits = visitService.findVisitsByPage(pageNum);
        PageInfo<Visit> page = new PageInfo(visits);

        model.addAttribute("page", page);
        return getPathIndex();
    }

}
