package com.ruoyi.project.admin.controller.home;

import com.ruoyi.framework.web.domain.R;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.admin.domain.User;
import com.ruoyi.project.admin.service.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    public R<String> login(User user) {
        if (user == null) {
            return R.fail("登录信息不能为空");
        }
        return null;
    }
}
