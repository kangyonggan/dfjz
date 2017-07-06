package com.kangyonggan.app.dfjz.model.dto;

import com.kangyonggan.app.dfjz.model.constants.Resp;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一接口响应实体类
 *
 * @author kangyonggan
 * @since 7/6/17
 */
@Data
public class CommonResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    private String respCode;

    /**
     * 响应描述
     */
    private String respMsg;

    /**
     * 结果
     */
    private String result;

}
