package com.kangyonggan.app.dfjz.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kangyonggan
 * @since 2017/4/10 0010
 */
@Data
public class ArticleCountDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 栏目代码
     */
    private String categoryCode;

    /**
     * 文章数量
     */
    private Integer articleCount;

}
