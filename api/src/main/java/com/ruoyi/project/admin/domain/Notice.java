package com.ruoyi.project.admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Data
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
}
