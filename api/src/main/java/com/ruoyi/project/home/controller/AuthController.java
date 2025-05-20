package com.ruoyi.project.home.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.home.domain.dto.LoginRequestDTO;
import com.ruoyi.project.home.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private IAuthService authService;

    /**
     * 用户登录
     */
    @PostMapping("/auth/login")
    public AjaxResult login(@RequestBody LoginRequestDTO loginRequestDTO) {
        // 生成令牌
        String token = authService.login(loginRequestDTO.getUsername(),
                loginRequestDTO.getPassword());
        return AjaxResult.success("登录成功")
                .put(Constants.TOKEN, token);
    }

    /**
     * 用户注销
     */
    @PostMapping("/auth/logout")
    public AjaxResult logout() {
        authService.logout(SecurityUtils.getUsername());
        return AjaxResult.success("退出成功");
    }
}
