package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 丢失物品对象 ss_lost_item
 * 
 * @author sinvon
 * @date 2025-04-19
 */
public class LostItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 丢失物品的id */
    private Integer id;

    /** 物品标题 */
    @Excel(name = "物品标题")
    private String title;

    /** 详细描述 */
    @Excel(name = "详细描述")
    private String description;

    /** 所属分类ID */
    @Excel(name = "所属分类ID")
    private Integer categoryId;

    /** 丢失地点 */
    @Excel(name = "丢失地点")
    private String lostLocation;

    /** 丢失时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "丢失时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lostTime;

    /** 认领状态 */
    @Excel(name = "认领状态")
    private Integer status;

    /** 发布者的id */
    @Excel(name = "发布者的id")
    private Integer publisherId;

    /** 图片URL */
    @Excel(name = "图片URL")
    private String imageUrl;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactInfo;

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

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setCategoryId(Integer categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() 
    {
        return categoryId;
    }

    public void setLostLocation(String lostLocation) 
    {
        this.lostLocation = lostLocation;
    }

    public String getLostLocation() 
    {
        return lostLocation;
    }

    public void setLostTime(Date lostTime) 
    {
        this.lostTime = lostTime;
    }

    public Date getLostTime() 
    {
        return lostTime;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setPublisherId(Integer publisherId) 
    {
        this.publisherId = publisherId;
    }

    public Integer getPublisherId() 
    {
        return publisherId;
    }

    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }

    public void setContactInfo(String contactInfo) 
    {
        this.contactInfo = contactInfo;
    }

    public String getContactInfo() 
    {
        return contactInfo;
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
            .append("description", getDescription())
            .append("categoryId", getCategoryId())
            .append("lostLocation", getLostLocation())
            .append("lostTime", getLostTime())
            .append("status", getStatus())
            .append("publisherId", getPublisherId())
            .append("imageUrl", getImageUrl())
            .append("contactInfo", getContactInfo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
