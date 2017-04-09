package com.kangyonggan.app.dfjz.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 文章的目录
 *
 * @author kangyonggan
 * @since 2017/4/9 0009
 */
@Data
public class Toc {

    /**
     * 层次级别
     */
    private int level;

    /**
     * 排序
     */
    private int sort;

    /**
     * 名称
     */
    private String name;

    /**
     * 子目录
     */
    private List<Toc> childrens;

}
