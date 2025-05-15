package com.ruoyi.project.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 公告新闻对象 ss_news
 * 
 * @author sinvon
 * @date 2025-04-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ss_news")
public class News extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告ID */
    private Integer id;

    /** 标题  */
    @Excel(name = "标题 ")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 类型 */
    @Excel(name = "类型")
    private Integer type;

    /** 发布者 */
    @Excel(name = "发布者")
    private Integer publisherId;

    /** 是否已删除 */
    private Boolean isDeleted;
}
