package com.ruoyi.project.home.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.common.domain.User;
import com.ruoyi.project.home.service.IAuthService;
import com.ruoyi.project.home.service.IHomeUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HomeAuthServiceImpl implements IAuthService {

    @Resource
    private IHomeUserService homeUserService;
    @Resource
    private HomeTokenService homeTokenService;
    @Resource
    private RedisCache redisCache;

    @Override
    public String login(String username, String password) {

        // 增加登录次数校验
        String loginKey = "login_record:" + username;
        Integer errorCount = redisCache.getCacheObject(loginKey);
        if (errorCount != null && errorCount >= 5) {
            throw new ServiceException("密码错误次数过多，请10分钟后再试");
        }

        // 验证用户信息
        User user = homeUserService.selectUserByUsername(username);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        if (!SecurityUtils.matchesPassword(password, user.getPassword())) {
            throw new ServiceException("密码错误");
        }
        if (user.getStatus() != User.Status.NORMAL) {
            throw new ServiceException("账户已停用");
        }

        // 获取 loginUser
        return homeTokenService.createToken(user);
    }

    @Override
    public void logout(String username) {
        homeTokenService.deleteToken(username);
    }
}
