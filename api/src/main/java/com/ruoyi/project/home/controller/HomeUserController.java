package com.ruoyi.project.home.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.domain.User;
import com.ruoyi.project.home.domain.dto.LoginRequestDTO;
import com.ruoyi.project.home.domain.dto.TokenRequestDTO;
import com.ruoyi.project.home.domain.vo.UserVO;
import com.ruoyi.project.home.service.IAuthService;
import com.ruoyi.project.home.service.IHomeUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/home/user")
public class HomeUserController extends BaseController {

    @Resource
    private IHomeUserService homeUserService;
    @Resource
    private IAuthService authService;

    /**
     * 用户登录（新）
     */
    @PostMapping("/auth/login")
    public AjaxResult login(@RequestBody LoginRequestDTO loginRequest) {
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return AjaxResult.success("登录成功").put(Constants.TOKEN, token);
    }

    /**
     * 用户注册（旧功能升级）
     */
    @PostMapping("/register")
    public AjaxResult register(@RequestBody @Validated UserVO userVo) {
        // 参数校验
        if (userVo.getPassword() == null || userVo.getPassword().isEmpty()) {
            return AjaxResult.error("密码不能为空");
        }

        User user = new User();
        BeanUtils.copyProperties(userVo, user);

        // 密码加密
        user.setPassword(user.getPassword());
        user.setRole(User.Role.COMMON_USER);
        user.setStatus(User.Status.NORMAL);
        user.setIsDeleted(false);

        // 处理用户名逻辑
        if (user.getUsername() == null) {
            if (user.getPhone() != null) {
                user.setUsername(generateDefaultUsername(user.getPhone()));
            } else if (user.getEmail() != null) {
                user.setUsername(user.getEmail().split("@")[0]);
            }
        }

        return toAjax(homeUserService.registerUser(user));
    }

    /**
     * 用户注销（新）
     */
    @PostMapping("/auth/logout")
    public AjaxResult logout() {
        authService.logout(SecurityUtils.getUsername());
        return AjaxResult.success("退出成功");
    }

    /**
     * 用户信息编辑（旧功能升级）
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody @Validated UserVO userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);

        // 禁止修改敏感字段
        user.setPassword(null);
        user.setRole(null);
        user.setStatus(null);

        return toAjax(homeUserService.updateUserInfo(user));
    }

    /**
     * 获取当前用户信息（新）
     */
    @PostMapping("/getInfo")
    public AjaxResult getInfo() {
        return AjaxResult.success().put("user", homeUserService.getCurrentUserInfo());
    }

    // 以下为管理接口
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody User user) {
        startPage();
        List<User> list = homeUserService.selectUserList(user);
        return getDataTable(list);
    }

    @GetMapping("/{userId}")
    public AjaxResult getInfo(@PathVariable Integer userId) {
        return AjaxResult.success(homeUserService.getById(userId));
    }

    @PostMapping
    public AjaxResult add(@Validated @RequestBody User user) {
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(homeUserService.createUser(user));
    }

    @PutMapping
    public AjaxResult update(@Validated @RequestBody User user) {
        user.setPassword(null); // 禁止更新密码
        return toAjax(homeUserService.updateUser(user));
    }

    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable List<Integer> userIds) {
        return toAjax(homeUserService.deleteUsers(userIds));
    }

    @PutMapping("/status")
    public AjaxResult changeStatus(@RequestBody User user) {
        return toAjax(homeUserService.updateUserStatus(user.getId(), user.getStatus()));
    }

    @PutMapping("/avatar")
    public AjaxResult updateAvatar(@RequestBody User user) {
        return toAjax(homeUserService.updateUserAvatar(user.getId(), user.getAvatar()));
    }

    private String generateDefaultUsername(String phone) {
        return "用户" + phone.substring(0, 3) + "***" + phone.substring(7);
    }
}