package com.ruoyi.project.admin.domain;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.*;

import java.util.Date;

/**
 * 通知对象 ss_notice
 * 
 * @author sinvon
 * @date 2025-04-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ss_notice")
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
    private Type type;

    /** 更新时间 */
    private Date upateTime;

    /** 是否已删除 */
    private Boolean isDeleted;

    @ToString
    @Getter
    @AllArgsConstructor
    public enum Type {

        // 普通
        COMMON(0, "普通"),
        // 紧急
        EMERGENCY(1, "紧急");
        @JsonValue
        @EnumValue
        private final Integer value;
        private final String name;
    }

}
