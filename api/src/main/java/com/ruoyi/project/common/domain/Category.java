package com.ruoyi.project.common.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 物品类别对象 ss_category
 *
 * @author sinvon
 * @since 2025-04-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ss_category")
public class Category extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 类别id
     */
    @Excel(name = "类别id")
    private Integer id;

    /**
     * 类别名称
     */
    @Excel(name = "类别名称")
    private String name;

    /**
     * 类型父类id
     */
    @Excel(name = "类型父类id")
    private Integer parentId;

    /**
     * 是否已删除
     */
    @Excel(name = "是否已删除")
    private Boolean isDeleted;


    @TableField(exist = false)
    private String parentName;

    @TableField(exist = false)
    private List<Category> children;
}
