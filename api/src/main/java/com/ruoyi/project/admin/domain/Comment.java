package com.ruoyi.project.admin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 评论对象 ss_comment
 * 
 * @author sinvon
 * @date 2025-04-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Comment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论ID */
    private Integer id;

    /** 评论者ID */
    @Excel(name = "评论者ID")
    private Integer userId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private Integer content;

    /** 关联的失物ID */
    @Excel(name = "关联的失物ID")
    private Integer itemId;

    /** 关联的文章ID */
    @Excel(name = "关联的文章ID")
    private Integer articleId;

    /** 父评论ID */
    @Excel(name = "父评论ID")
    private Integer parentCommentId;

    /** 是否已删除 */
    private Boolean isDeleted;
}
