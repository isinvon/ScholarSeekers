package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 通知对象 ss_notice
 * 
 * @author sinvon
 * @date 2025-04-19
 */
public class Notice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 通知ID */
    private Long id;

    /** 接收用户ID */
    @Excel(name = "接收用户ID")
    private Long userId;

    /** 通知内容 */
    @Excel(name = "通知内容")
    private String content;

    /** 通知类型 */
    @Excel(name = "通知类型")
    private Long type;

    /** 更新时间 */
    private Date upateTime;

    /** 是否已删除 */
    private Boolean isDeleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }

    public void setUpateTime(Date upateTime) 
    {
        this.upateTime = upateTime;
    }

    public Date getUpateTime() 
    {
        return upateTime;
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
            .append("type", getType())
            .append("createTime", getCreateTime())
            .append("upateTime", getUpateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
