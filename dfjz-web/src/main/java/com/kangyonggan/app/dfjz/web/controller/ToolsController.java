package com.kangyonggan.app.dfjz.web.controller;

import com.kangyonggan.app.dfjz.biz.service.DictionaryService;
import com.kangyonggan.app.dfjz.biz.service.ToolService;
import com.kangyonggan.app.dfjz.biz.util.PropertiesUtil;
import com.kangyonggan.app.dfjz.common.CompressorUtil;
import com.kangyonggan.app.dfjz.common.GsonUtil;
import com.kangyonggan.app.dfjz.common.MarkdownUtil;
import com.kangyonggan.app.dfjz.common.QrCodeUtil;
import com.kangyonggan.app.dfjz.model.vo.Dictionary;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/4/21 0021
 */
@Controller
@RequestMapping("tools")
@Log4j2
public class ToolsController extends BaseController {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ToolService toolService;

    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

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

    /**
     * HTTP状态码
     *
     * @return
     */
    @RequestMapping(value = "http", method = RequestMethod.GET)
    public String http(Model model) {
        List<Dictionary> https = dictionaryService.findDictionariesByType("HTTP");

        model.addAttribute("https", https);
        return getPathRoot() + "/http";
    }

    /**
     * HTML转义字符
     *
     * @return
     */
    @RequestMapping(value = "html", method = RequestMethod.GET)
    public String html(Model model) {
        List<Dictionary> htmls = dictionaryService.findDictionariesByType("HTML");

        model.addAttribute("htmls", htmls);
        return getPathRoot() + "/html";
    }

    /**
     * XML格式化
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "xml", method = RequestMethod.GET)
    public String xml(Model model) {
        return getPathRoot() + "/xml";
    }

    /**
     * XML格式化
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = "xml", method = RequestMethod.POST)
    public String xml(@RequestParam("data") String data, Model model) {
        model.addAttribute("result", toolService.formatXml(data));
        return getPathRoot() + "/xml";
    }

    /**
     * SQL格式化
     *
     * @return
     */
    @RequestMapping(value = "sql", method = RequestMethod.GET)
    public String sql() {
        return getPathRoot() + "/sql";
    }

    /**
     * SQL格式化
     *
     * @param data
     * @param dialect
     * @param model
     * @return
     */
    @RequestMapping(value = "sql", method = RequestMethod.POST)
    public String sql(@RequestParam("data") String data,
                      @RequestParam(value = "dialect", required = false, defaultValue = "MySQL") String dialect,
                      Model model) {
        model.addAttribute("result", toolService.formatSql(data, dialect));
        return getPathRoot() + "/sql";
    }

    /**
     * JSON格式化
     *
     * @return
     */
    @RequestMapping(value = "json", method = RequestMethod.GET)
    public String json() {
        return getPathRoot() + "/json";
    }

    /**
     * JSON格式化
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = "json", method = RequestMethod.POST)
    public String json(@RequestParam("data") String data, Model model) {
        model.addAttribute("result", GsonUtil.format(data));
        return getPathRoot() + "/json";
    }

    /**
     * Markdown编辑器
     *
     * @return
     */
    @RequestMapping(value = "markdown", method = RequestMethod.GET)
    public String markdown() {
        return getPathRoot() + "/markdown";
    }

    /**
     * Markdown编辑器
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = "markdown", method = RequestMethod.POST)
    public String markdown(@RequestParam("data") String data, Model model) {
        model.addAttribute("result", MarkdownUtil.markdownToHtml(data));
        return getPathRoot() + "/markdown";
    }

    /**
     * JS压缩
     *
     * @return
     */
    @RequestMapping(value = "js", method = RequestMethod.GET)
    public String js() {
        return getPathRoot() + "/js";
    }

    /**
     * JS压缩
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = "js", method = RequestMethod.POST)
    public String js(@RequestParam("data") String data, Model model) {
        model.addAttribute("resultMap", CompressorUtil.compressJS(data));
        return getPathRoot() + "/js";
    }

    /**
     * CSS压缩
     *
     * @return
     */
    @RequestMapping(value = "css", method = RequestMethod.GET)
    public String css() {
        return getPathRoot() + "/css";
    }

    /**
     * CSS压缩
     *
     * @param data
     * @param model
     * @return
     */
    @RequestMapping(value = "css", method = RequestMethod.POST)
    public String css(@RequestParam("data") String data, Model model) {
        model.addAttribute("result", CompressorUtil.compressCSS(data));
        return getPathRoot() + "/css";
    }

    /**
     * 二维码
     *
     * @return
     */
    @RequestMapping(value = "qr", method = RequestMethod.GET)
    public String qr() {
        return getPathRoot() + "/qr";
    }

    /**
     * 二维码
     *
     * @param data
     * @param size
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "qr", method = RequestMethod.POST)
    public String qr(@RequestParam("data") String data, @RequestParam(value = "size", required = false, defaultValue = "200") int size,
                     Model model) throws Exception {
        if (StringUtils.isEmpty(data)) {
            return getPathRoot() + "/qr";
        }

        String qrName = "QR" + format.format(new Date()) + ".png";
        String name = PropertiesUtil.getProperties("file.root.path") + "upload/" + qrName;
        log.info("生成二维码的名称:" + qrName);
        try {
            QrCodeUtil.genQrCode(name, data, size);
            log.info("二维码生成成功");
        } catch (Exception e) {
            log.error("生成二维码失败", e);
            throw e;
        }
        model.addAttribute("result", qrName);
        return getPathRoot() + "/qr";
    }
}
