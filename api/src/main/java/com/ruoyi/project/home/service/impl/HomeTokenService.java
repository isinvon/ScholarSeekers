package com.ruoyi.project.home.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.project.common.domain.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class HomeTokenService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String TOKEN_KEY_PREFIX = "login_tokens:";

    public String createToken(User user) {
        String token = UUID.randomUUID().toString();

        // 存储用户信息（带过期时间）
        redisTemplate.opsForValue().set(
                TOKEN_KEY_PREFIX + "token:" + token,
                user,
                120,
                TimeUnit.MINUTES
        );

        // 维护用户-令牌关系
        redisTemplate.opsForSet().add(
                TOKEN_KEY_PREFIX + "user:" + user.getUsername(),
                token
        );
        redisTemplate.expire(
                TOKEN_KEY_PREFIX + "user:" + user.getUsername(),
                120,
                TimeUnit.MINUTES
        );

        return token;
    }

    /**
     * 删除用户所有令牌（完全退出）
     */
    public void deleteToken(String username) {
        String userKey = getUserKey(username);

        // 1. 获取所有关联令牌
        Set<String> tokens = Objects.requireNonNull(redisTemplate.opsForSet().members(userKey))
                .stream()
                .map(Object::toString)
                .collect(Collectors.toSet());

        if (CollectionUtils.isEmpty(tokens)) {
            return;
        }

        // 2. 删除所有令牌记录
        List<String> tokenKeys = tokens.stream()
                .map(this::getTokenKey)
                .collect(Collectors.toList());

        redisTemplate.delete(tokenKeys);

        // 3. 删除用户关联集合
        redisTemplate.delete(userKey);
    }

    // 完整配套方法
    private String getTokenKey(String token) {
        return Constants.LOGIN_TOKEN_KEY + "token:" + token;
    }

    private String getUserKey(String username) {
        return Constants.LOGIN_TOKEN_KEY + "user:" + username;
    }
}