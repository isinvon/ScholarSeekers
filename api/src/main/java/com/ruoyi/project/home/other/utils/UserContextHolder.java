package com.ruoyi.project.home.other.utils;

import com.ruoyi.project.home.other.constant.RedisConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserContextHolder {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void setCurrentUserId(Integer userId) {
        redisTemplate.opsForValue().set(RedisConstant.TOKEN_PREFIX, userId);
    }

    public Integer getCurrentUserId() {
        return (Integer) redisTemplate.opsForValue().get(RedisConstant.TOKEN_PREFIX);
    }

    public void clear() {

    }
}