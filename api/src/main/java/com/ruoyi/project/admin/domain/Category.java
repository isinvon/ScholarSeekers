package com.ruoyi.project.admin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 物品类别对象 ss_category
 * 
 * @author sinvon
 * @date 2025-04-19
 */
public class Category extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类别id */
    @Excel(name = "类别id")
    private Integer id;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String name;

    /** 类型父类id */
    @Excel(name = "类型父类id")
    private Integer parentId;

    /** 是否已删除 */
    @Excel(name = "是否已删除")
    private Boolean isDeleted;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setParentId(Integer parentId) 
    {
        this.parentId = parentId;
    }

    public Integer getParentId() 
    {
        return parentId;
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
            .append("name", getName())
            .append("parentId", getParentId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
