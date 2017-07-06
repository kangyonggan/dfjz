package com.kangyonggan.app.dfjz.web.controller;

import com.kangyonggan.app.dfjz.biz.service.ToolService;
import com.kangyonggan.app.dfjz.biz.util.PropertiesUtil;
import com.kangyonggan.app.dfjz.common.*;
import com.kangyonggan.app.dfjz.model.annotation.LogTime;
import com.kangyonggan.app.dfjz.model.constants.Resp;
import com.kangyonggan.app.dfjz.model.dto.CommonResponse;
import com.kangyonggan.app.dfjz.model.dto.IdCardResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 微服务接口
 *
 * @author kangyonggan
 * @since 7/6/17
 */
@RestController
@RequestMapping("service")
@Log4j2
public class ServiceController {

    @Autowired
    private ToolService toolService;

    /**
     * xml格式化
     *
     * @param data 待格式化的xml
     * @return
     */
    @RequestMapping(value = "xml", method = RequestMethod.POST)
    @LogTime
    public CommonResponse xml(@RequestParam("data") String data) {
        CommonResponse response = new CommonResponse();

        try {
            String result = XmlUtil.format(data);

            response.setRespCode(Resp.K0000.getRespCode());
            response.setRespCode(Resp.K0000.getRespMsg());
            response.setResult(result);
        } catch (Exception e) {
            log.warn("格式化xml失败", e);
            response.setRespCode(Resp.K9999.getRespCode());
            response.setRespMsg(e.getMessage());
        }

        return response;
    }

    /**
     * sql格式化
     *
     * @param data    待格式化的sql
     * @param dialect sql方言，默认为MySQL
     * @return
     */
    @RequestMapping(value = "sql", method = RequestMethod.POST)
    @LogTime
    public CommonResponse sql(@RequestParam("data") String data, @RequestParam(value = "dialect", required = false, defaultValue = "MySQL") String dialect) {
        CommonResponse response = new CommonResponse();

        try {
            String result = toolService.formatSql(data, dialect);

            response.setRespCode(Resp.K0000.getRespCode());
            response.setRespCode(Resp.K0000.getRespMsg());
            response.setResult(result);
        } catch (Exception e) {
            log.warn("格式化sql失败", e);
            response.setRespCode(Resp.K9999.getRespCode());
            response.setRespMsg(e.getMessage());
        }

        return response;
    }

    /**
     * json格式化
     *
     * @param data 待格式化的json
     * @return
     */
    @RequestMapping(value = "json", method = RequestMethod.POST)
    @LogTime
    public CommonResponse json(@RequestParam("data") String data) {
        CommonResponse response = new CommonResponse();

        try {
            String result = GsonUtil.format(data);

            response.setRespCode(Resp.K0000.getRespCode());
            response.setRespCode(Resp.K0000.getRespMsg());
            response.setResult(result);
        } catch (Exception e) {
            log.warn("格式化json失败", e);
            response.setRespCode(Resp.K9999.getRespCode());
            response.setRespMsg(e.getMessage());
        }

        return response;
    }

    /**
     * markdown转html
     *
     * @param data 待转化的markdown
     * @return
     */
    @RequestMapping(value = "markdown", method = RequestMethod.POST)
    @LogTime
    public CommonResponse markdown(@RequestParam("data") String data) {
        CommonResponse response = new CommonResponse();

        try {
            String result = MarkdownUtil.markdownToHtml(data);

            response.setRespCode(Resp.K0000.getRespCode());
            response.setRespCode(Resp.K0000.getRespMsg());
            response.setResult(result);
        } catch (Exception e) {
            log.warn("markdown转html失败", e);
            response.setRespCode(Resp.K9999.getRespCode());
            response.setRespMsg(e.getMessage());
        }

        return response;
    }

    /**
     * js压缩
     *
     * @param data 待压缩的js
     * @return
     */
    @RequestMapping(value = "markdown", method = RequestMethod.POST)
    @LogTime
    public CommonResponse js(@RequestParam("data") String data) {
        CommonResponse response = new CommonResponse();

        try {
            Map<String, String> resultMap = CompressorUtil.compressJS(data);

            response.setRespCode(Resp.K0000.getRespCode());
            response.setRespCode(Resp.K0000.getRespMsg());
            response.setResult(resultMap.get("result"));
        } catch (Exception e) {
            log.warn("js压缩失败", e);
            response.setRespCode(Resp.K9999.getRespCode());
            response.setRespMsg(e.getMessage());
        }

        return response;
    }

    /**
     * css压缩
     *
     * @param data 待压缩的css
     * @return
     */
    @RequestMapping(value = "css", method = RequestMethod.POST)
    @LogTime
    public CommonResponse css(@RequestParam("data") String data) {
        CommonResponse response = new CommonResponse();

        try {
            String result = CompressorUtil.compressCSS(data);

            response.setRespCode(Resp.K0000.getRespCode());
            response.setRespCode(Resp.K0000.getRespMsg());
            response.setResult(result);
        } catch (Exception e) {
            log.warn("css压缩失败", e);
            response.setRespCode(Resp.K9999.getRespCode());
            response.setRespMsg(e.getMessage());
        }

        return response;
    }

    /**
     * 生成二维码
     *
     * @param data 二维码内容
     * @param size 二维码大小，默认200x200
     * @return
     */
    @RequestMapping(value = "qr", method = RequestMethod.POST)
    @LogTime
    public CommonResponse qr(@RequestParam("data") String data, @RequestParam(value = "size", required = false, defaultValue = "200") int size) {
        CommonResponse response = new CommonResponse();

        String qrName = "QR" + System.nanoTime() + ".png";
        String name = PropertiesUtil.getProperties("file.root.path") + "upload/" + qrName;
        log.info("生成二维码的名称:" + qrName);

        try {
            QrCodeUtil.genQrCode(name, data, size);

            response.setRespCode(Resp.K0000.getRespCode());
            response.setRespMsg(Resp.K0000.getRespMsg());
            response.setResult("https://kangyonggan.com/upload/" + qrName);
        } catch (Exception e) {
            log.warn("生成二维码失败", e);
            response.setRespCode(Resp.K9999.getRespCode());
            response.setRespMsg(e.getMessage());
        }
        return response;
    }

    /**
     * 二维码解析
     *
     * @param data 二维码地址
     * @return
     */
    @RequestMapping(value = "qr", method = RequestMethod.POST)
    @LogTime
    public CommonResponse qr(@RequestParam("data") String data) {
        CommonResponse response = new CommonResponse();

        try {
            String result = QrCodeUtil.decode(data);

            response.setRespCode(Resp.K0000.getRespCode());
            response.setRespMsg(Resp.K0000.getRespMsg());
            response.setResult(result);
        } catch (Exception e) {
            log.warn("二维码解析失败", e);
            response.setRespCode(Resp.K9999.getRespCode());
            response.setRespMsg(e.getMessage());
        }
        return response;
    }

    /**
     * 二维码解析
     *
     * @param data 二维码图片
     * @return
     */
    @RequestMapping(value = "qr", method = RequestMethod.POST)
    @LogTime
    public CommonResponse qr(@RequestParam(value = "data") MultipartFile data) {
        CommonResponse response = new CommonResponse();

        try {
            String result = QrCodeUtil.decode(data.getInputStream());

            response.setRespCode(Resp.K0000.getRespCode());
            response.setRespMsg(Resp.K0000.getRespMsg());
            response.setResult(result);
        } catch (Exception e) {
            log.warn("二维码解析失败", e);
            response.setRespCode(Resp.K9999.getRespCode());
            response.setRespMsg(e.getMessage());
        }
        return response;
    }

    /**
     * 判断是否是有效身份证
     *
     * @param data 身份证号码
     * @return
     */
    @RequestMapping(value = "idcard", method = RequestMethod.POST)
    @LogTime
    public CommonResponse idcard(@RequestParam(value = "data") String data) {
        CommonResponse response = new CommonResponse();

        try {
            String res[] = IDCardUtil.isIdCard(data);

            if ("0".equals(res[0])) {
                response.setRespCode(Resp.K0000.getRespCode());
                response.setRespMsg(Resp.K0000.getRespMsg());
            } else {
                response.setRespCode(Resp.K9999.getRespCode());
                response.setRespMsg(res[1]);
            }
        } catch (Exception e) {
            log.warn("判断是否是身份证异常", e);
            response.setRespCode(Resp.K9999.getRespCode());
            response.setRespMsg(e.getMessage());
        }
        return response;
    }

    /**
     * 身份生成证
     *
     * @param prov 省份编码，比如：340321，默认随机
     * @param startAge 最小年龄，默认1
     * @param endAge 最大年龄，默认100
     * @param sex 性别，0:男，1:女，默认0
     * @param len 身份证长度，15：15位身份证，18：18位身份证，默认随机
     * @param size 生成数量，默认10个
     * @return
     */
    @RequestMapping(value = "gencard", method = RequestMethod.POST)
    @LogTime
    public IdCardResponse gencard(@RequestParam(value = "prov", required = false, defaultValue = "") String prov,
                                  @RequestParam(value = "startAge", required = false, defaultValue = "1") int startAge,
                                  @RequestParam(value = "endAge", required = false, defaultValue = "100") int endAge,
                                  @RequestParam(value = "sex", required = false, defaultValue = "") String sex,
                                  @RequestParam(value = "len", required = false, defaultValue = "-1") int len,
                                  @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        IdCardResponse response = new IdCardResponse();

        if (size < 0 || size > 100) {
            response.setRespCode(Resp.K0001.getRespCode());
            response.setRespMsg("生成数量控制在1~100之间（包括）");

            return response;
        }

        try {
            List<String> list = IDCardUtil.genIdCard(prov, startAge, endAge, sex, len, size);

            response.setRespCode(Resp.K0000.getRespCode());
            response.setRespMsg(Resp.K0000.getRespMsg());
            response.setResults(list);
        } catch (Exception e) {
            log.warn("生成身份证号码异常", e);
            response.setRespCode(Resp.K9999.getRespCode());
            response.setRespMsg(e.getMessage());
        }

        return response;
    }
}
