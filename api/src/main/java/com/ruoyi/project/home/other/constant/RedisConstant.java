package com.ruoyi.project.home.other.constant;

import lombok.Getter;

/**
 * Redis常量
 */
@Getter
public class RedisConstant {
    public static final String TOKEN_PREFIX = "auth:token:";
    public static final long TOKEN_EXPIRE_MINUTES = 120;
}
