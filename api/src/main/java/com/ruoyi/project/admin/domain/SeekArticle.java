package com.ruoyi.project.admin.domain;

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

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setUserId(Integer userId) 
    {
        this.userId = userId;
    }

    public Integer getUserId() 
    {
        return userId;
    }

    public void setRelatedItemId(Integer relatedItemId) 
    {
        this.relatedItemId = relatedItemId;
    }

    public Integer getRelatedItemId() 
    {
        return relatedItemId;
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
            .append("title", getTitle())
            .append("content", getContent())
            .append("userId", getUserId())
            .append("relatedItemId", getRelatedItemId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
