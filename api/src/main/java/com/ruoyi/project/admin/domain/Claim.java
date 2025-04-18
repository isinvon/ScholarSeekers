package com.ruoyi.project.admin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Data
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
}
