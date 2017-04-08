package com.kangyonggan.app.dfjz.model.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "article")
public class Article implements Serializable {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 栏目代码
     */
    @Column(name = "category_code")
    private String categoryCode;

    /**
     * 栏目名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 访问量
     */
    @Column(name = "visit_count")
    private Integer visitCount;

    /**
     * 评论量
     */
    @Column(name = "comment_count")
    private Integer commentCount;

    /**
     * 是否允许评论:{0:不允许, 1:允许}
     */
    @Column(name = "is_comment")
    private Byte isComment;

    /**
     * 是否置顶:{0:未置顶, 1:已置顶}
     */
    @Column(name = "is_stick")
    private Byte isStick;

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

    /**
     * 文章内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}