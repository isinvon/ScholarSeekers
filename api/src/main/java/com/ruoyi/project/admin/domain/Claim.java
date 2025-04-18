package com.ruoyi.project.admin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 认领记录对象 ss_claim
 * 
 * @author sinvon
 * @date 2025-04-19
 */
public class Claim extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 认领ID */
    private Integer id;

    /** 被认领物品ID */
    @Excel(name = "被认领物品ID")
    private Integer itemId;

    /** 认领人ID */
    @Excel(name = "认领人ID")
    private Integer claimantId;

    /** 认领凭证（如描述匹配信息） */
    @Excel(name = "认领凭证", readConverterExp = "如=描述匹配信息")
    private String proof;

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

    public void setItemId(Integer itemId) 
    {
        this.itemId = itemId;
    }

    public Integer getItemId() 
    {
        return itemId;
    }

    public void setClaimantId(Integer claimantId) 
    {
        this.claimantId = claimantId;
    }

    public Integer getClaimantId() 
    {
        return claimantId;
    }

    public void setProof(String proof) 
    {
        this.proof = proof;
    }

    public String getProof() 
    {
        return proof;
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
            .append("itemId", getItemId())
            .append("claimantId", getClaimantId())
            .append("proof", getProof())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDeleted", getIsDeleted())
            .toString();
    }
}
