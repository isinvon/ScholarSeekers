package com.ruoyi.project.admin.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ss_lost_item")
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
}
