package com.ruoyi.project.admin.controller.home;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.framework.web.domain.R;
import com.ruoyi.project.admin.domain.User;
import com.ruoyi.project.admin.domain.vo.UserVo;
import com.ruoyi.project.admin.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Home Controller
 *
 * @author sinvon
 * @date 2025年4月19日02:57:47
 */
@RestController
@RequestMapping("/home/user")
public class HomeUserController {
    @Resource
    private IUserService userService;

    /**
     * 查询用户列表
     */
    @PostMapping("/login")
    public R<String> login(UserVo userVo) {
        if (userVo == null) {
            return R.fail("登录信息不能为空");
        }
        // 手机号登录
        if (userVo.getPhone() != null && userVo.getPassword() != null) {
            User one = userService.lambdaQuery().eq(User::getPhone, userVo.getPhone()).one();
            if (one != null && one.getPassword().equals(userVo.getPassword())) {
                return R.ok("登录成功");
            }
        }
        // 邮箱登录
        if (userVo.getEmail() != null && userVo.getPassword() != null) {
            User one = userService.lambdaQuery().eq(User::getEmail, userVo.getEmail()).one();
            if (one != null && one.getPassword().equals(userVo.getPassword())) {
                return R.ok("登录成功");
            }
        }
        return R.fail("请使用手机号或邮箱登录");
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public R<String> register(UserVo userVo) {
        if (userVo == null) {
            return R.fail("注册信息不能为空");
        }
        // 手机号注册
        if (userVo.getPhone() != null && userVo.getPassword() != null) {
            User one = userService.lambdaQuery().eq(User::getPhone, userVo.getPhone()).one();
            if (one == null) {
                User user = new User();
                user.setPhone(userVo.getPhone());
                user.setPassword(userVo.getPassword());
                userService.save(user);
                return R.ok("注册成功");
            } else {
                return R.fail("手机号已被注册");
            }
        }
        // 邮箱注册
        if (userVo.getEmail() != null && userVo.getPassword() != null) {
            User one = userService.lambdaQuery().eq(User::getEmail, userVo.getEmail()).one();
            if (one == null) {
                User user = new User();
                user.setEmail(userVo.getEmail());
                user.setPassword(userVo.getPassword());
                userService.save(user);
                return R.ok("注册成功");
            } else {
                return R.fail("邮箱已被注册");
            }
        }
        return R.fail("请使用手机号或邮箱注册");
    }

    @PostMapping("/logout")
    public R<String> logout() {
        return R.ok("退出成功");
    }

    // 编辑用户信息
    @PostMapping("/edit")
    public R<String> edit(UserVo userVo) {
        if (userVo == null) {
            return R.fail("编辑信息不能为空");
        }
        User user = new User();
        BeanUtil.copyProperties(userVo, user);
        boolean update = userService.lambdaUpdate()
                .eq(User::getId, userVo.getId())
                .update(user);
        return update ? R.ok("编辑成功") : R.fail("编辑失败");
    }
}
