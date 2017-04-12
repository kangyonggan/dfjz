package com.kangyonggan.app.dfjz.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kangyonggan
 * @since 2017/4/10 0010
 */
@Data
public class VisitCountDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    private Long articleId;

    /**
     * f访问量
     */
    private Integer visitCount;

}
