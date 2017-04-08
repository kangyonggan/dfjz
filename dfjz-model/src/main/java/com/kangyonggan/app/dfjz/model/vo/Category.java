package com.kangyonggan.app.dfjz.model.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "category")
public class Category implements Serializable {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 栏目代码
     */
    private String code;

    /**
     * 栏目名称
     */
    private String name;

    /**
     * 栏目排序
     */
    private Integer sort;

    /**
     * 父栏目代码
     */
    private String pcode;

    /**
     * 栏目头图
     */
    private String picture;

    /**
     * 文章数量
     */
    @Column(name = "article_count")
    private Integer articleCount;

    /**
     * 逻辑删除:{0:未删除, 1:已删除}
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}