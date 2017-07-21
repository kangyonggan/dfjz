package com.kangyonggan.app.dfjz.web.controller;

import com.kangyonggan.app.dfjz.web.util.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传
 *
 * @author kangyonggan
 * @since 7/20/17
 */
@Controller
@RequestMapping("file")
public class FileController extends BaseController {

    /**
     * 文件上传
     *
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String upload() {
        return getPathIndex();
    }

    /**
     * 文件上传
     *
     * @param files
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("files") List<MultipartFile> files) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < files.size(); i++) {
            try {
                if (files.get(i).isEmpty()) {
                    continue;
                }
                FileUpload.upload(files.get(i));
                result.append("https://kangyonggan.com/upload/").append(files.get(i).getOriginalFilename()).append("\n\n");
            } catch (FileUploadException e) {
                return "文件上传失败:" + e.getMessage();
            }
        }

        return result.toString();
    }

}
