package com.ruoyi.project.admin.domain;

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

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }

    public void setUserId(Integer userId) 
    {
        this.userId = userId;
    }

    public Integer getUserId() 
    {
        return userId;
    }

    public void setContent(Integer content) 
    {
        this.content = content;
    }

    public Integer getContent() 
    {
        return content;
    }

    public void setItemId(Integer itemId) 
    {
        this.itemId = itemId;
    }

    public Integer getItemId() 
    {
        return itemId;
    }

    public void setArticleId(Integer articleId) 
    {
        this.articleId = articleId;
    }

    public Integer getArticleId() 
    {
        return articleId;
    }

    public void setParentCommentId(Integer parentCommentId) 
    {
        this.parentCommentId = parentCommentId;
    }

    public Integer getParentCommentId() 
    {
        return parentCommentId;
    }

    public void setIsDeleted(Boolean isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("itemId", getItemId())
            .append("articleId", getArticleId())
            .append("parentCommentId", getParentCommentId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
