package com.ruoyi.project.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 帮助与反馈对象 ss_feedback
 * 
 * @author sinvon
 * @date 2025-04-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ss_feedback")
public class Feedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 反馈ID */
    private Integer id;

    /** 提交用户ID */
    @Excel(name = "提交用户ID")
    private Integer userId;

    /** 反馈内容 */
    @Excel(name = "反馈内容")
    private String content;

    /** 处理状态 */
    @Excel(name = "处理状态")
    private Integer status;

    /** 是否已删除 */
    @Excel(name = "是否已删除")
    private Boolean isDeleted;
}
