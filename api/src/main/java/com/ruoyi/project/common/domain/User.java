package com.ruoyi.project.common.domain;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户对象 ss_user
 *
 * @author sinvon
 * @date 2025-04-19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ss_user")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    private String username;

    /**
     * 密码
     */
    @Excel(name = "密码")
    private String password;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String phone;

    /**
     * 角色
     */
    @Excel(name = "角色")
    private Role role;

    /**
     * 头像
     */
    @Excel(name = "头像")
    private String avatar;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 学院
     */
    @Excel(name = "学院")
    private String college;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private Integer sex;

    /**
     * 班级
     */
    @Excel(name = "班级")
    private String grade;

    /**
     * 标签
     */
    @Excel(name = "标签")
    private String tag;

    /**
     * 住址
     */
    @Excel(name = "住址")
    private String address;

    /**
     * 个人介绍
     */
    @Excel(name = "个人介绍")
    private String introduction;

    /**
     * 账号状态（默认 0 为正常）
     */
    @Excel(name = "账号状态", readConverterExp = "默=认,0=,为=正常")
    private Status status;

    /**
     * 是否已删除
     */
    private Boolean isDeleted;

    @ToString
    @Getter
    @AllArgsConstructor
    public enum Role {
        /**
         * 普通用户
         */
        COMMON_USER(0, "普通用户"),
        /**
         * 管理员
         */
        ADMIN(1, "管理员");

        @JsonValue
        @EnumValue
        private final Integer value;
        private final String name;
    }

    @ToString
    @Getter
    @AllArgsConstructor
    public enum Status {
        /**
         * 正常
         */
        NORMAL(0, "正常"),
        /**
         * 停用
         */
        DISABLE(1, "停用"),
        /**
         * 锁定
         */
        LOCKED(2, "锁定");
        @JsonValue
        @EnumValue
        private final Integer value;
        private final String name;
    }

}
