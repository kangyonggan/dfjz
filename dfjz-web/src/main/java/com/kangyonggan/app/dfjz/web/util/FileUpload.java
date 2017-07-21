package com.kangyonggan.app.dfjz.web.util;

import com.kangyonggan.app.dfjz.biz.util.PropertiesUtil;
import com.kangyonggan.app.dfjz.model.constants.AppConstants;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author kangyonggan
 * @since 2016/12/6
 */
public class FileUpload {

    /**
     * 上传文件
     *
     * @param file
     * @throws Exception
     */
    public static void upload(MultipartFile file) throws FileUploadException {
        File newFile = new File(PropertiesUtil.getProperties(AppConstants.FILE_PATH_ROOT) + AppConstants.FILE_UPLOAD + file.getOriginalFilename());
        if (newFile.exists()) {
            throw new FileUploadException("文件已存在:" + newFile.getName());
        }

        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new FileUploadException("文件已存在:" + newFile.getName());
        }
    }
}
