package com.ruoyi.project.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 寻物启事对象 ss_seek_article
 * 
 * @author sinvon
 * @date 2025-04-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ss_seek_article")
public class SeekArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章ID */
    private Integer id;

    /** 寻物标题 */
    @Excel(name = "寻物标题")
    private String title;

    /** 详细内容 */
    @Excel(name = "详细内容")
    private String content;

    /** author_id */
    @Excel(name = "author_id")
    private Integer userId;

    /** 关联的失物ID (可选) */
    @Excel(name = "关联的失物ID (可选)")
    private Integer relatedItemId;

    /** 是否已删除 */
    private Boolean isDeleted;
}
