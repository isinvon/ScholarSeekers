package com.ruoyi.project.home.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.common.domain.User;
import com.ruoyi.project.home.domain.vo.UserVO;
import com.ruoyi.project.home.mapper.HomeUserMapper;
import com.ruoyi.project.home.service.IHomeUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class HomeUserServiceImpl extends ServiceImpl<HomeUserMapper, User> implements IHomeUserService {
    private final HomeUserMapper homeUserMapper;

    public HomeUserServiceImpl(HomeUserMapper homeUserMapper) {
        this.homeUserMapper = homeUserMapper;
    }

    @Override
    public List<User> selectUserList(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getIsDeleted, false)
                .like(user.getUsername() != null, User::getUsername, user.getUsername())
                .eq(user.getStatus() != null, User::getStatus, user.getStatus())
                .orderByDesc(User::getCreateTime);
        return this.list(wrapper);
    }

    @Override
    public boolean updateUserStatus(Integer userId, User.Status status) {
        User user = this.getById(userId);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        user.setStatus(status);
        return this.updateById(user);
    }

    @Override
    public boolean updateAvatar(Integer userId, String avatarUrl) {
        User user = this.getById(userId);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        user.setAvatar(avatarUrl);
        return this.updateById(user);
    }

    @Override
    public User selectUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return this.getOne(wrapper);
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @Override
    public UserVO getCurrentUserInfo() {
        Long userId = SecurityUtils.getUserId();
        User user = this.getById(userId);
        return BeanUtil.copyProperties(user, UserVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createUser(User user) {
        // 校验手机号唯一性
        if (StringUtils.isNotBlank(user.getPhone())) {
            Long count = homeUserMapper.selectCount(new LambdaQueryWrapper<User>()
                    .eq(User::getPhone, user.getPhone()));
            if (count > 0) {
                throw new ServiceException("手机号已存在");
            }
        }

        // 校验邮箱唯一性
        if (StringUtils.isNotBlank(user.getEmail())) {
            Long count = homeUserMapper.selectCount(new LambdaQueryWrapper<User>()
                    .eq(User::getEmail, user.getEmail()));
            if (count > 0) {
                throw new ServiceException("邮箱已存在");
            }
        }

        // 设置默认值
        user.setRole(User.Role.COMMON_USER);
        user.setStatus(User.Status.NORMAL);
        user.setIsDeleted(false);

        // 加密密码
        if (StringUtils.isBlank(user.getPassword())) {
            throw new ServiceException("密码不能为空");
        }
        // 加密密码 user.getPassword()
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));

        return homeUserMapper.insert(user) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(User user) {
        // 禁止修改字段
        user.setPassword(null);
        user.setRole(null);
        user.setStatus(null);
        user.setIsDeleted(null);

        // 校验用户存在
        User existing = homeUserMapper.selectById(user.getId());
        if (existing == null) {
            throw new ServiceException("用户不存在");
        }

        // 更新前校验唯一性
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId, user.getId());

        // 手机号修改校验
        if (StringUtils.isNotBlank(user.getPhone())
                && !user.getPhone().equals(existing.getPhone())) {
            Long count = homeUserMapper.selectCount(new LambdaQueryWrapper<User>()
                    .eq(User::getPhone, user.getPhone()));
            if (count > 0) {
                throw new ServiceException("手机号已被使用");
            }
            wrapper.set(User::getPhone, user.getPhone());
        }

        // 邮箱修改校验
        if (StringUtils.isNotBlank(user.getEmail())
                && !user.getEmail().equals(existing.getEmail())) {
            Long count = homeUserMapper.selectCount(new LambdaQueryWrapper<User>()
                    .eq(User::getEmail, user.getEmail()));
            if (count > 0) {
                throw new ServiceException("邮箱已被使用");
            }
            wrapper.set(User::getEmail, user.getEmail());
        }

        // 动态更新其他字段
        if (StringUtils.isNotBlank(user.getUsername())) {
            wrapper.set(User::getUsername, user.getUsername());
        }
        if (user.getSex() != null) {
            wrapper.set(User::getSex, user.getSex());
        }
        if (StringUtils.isNotBlank(user.getAvatar())) {
            wrapper.set(User::getAvatar, user.getAvatar());
        }

        return homeUserMapper.update(null, wrapper) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUsers(List<Integer> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            throw new ServiceException("用户ID列表不能为空");
        }

        // 逻辑删除，设置is_deleted为true
        return homeUserMapper.update(null,
                new LambdaUpdateWrapper<User>()
                        .set(User::getIsDeleted, true)
                        .in(User::getId, userIds)) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean registerUser(User user) {
        // 注册专用校验
        if (StringUtils.isAllBlank(user.getPhone(), user.getEmail())) {
            throw new ServiceException("手机号或邮箱必须填写一项");
        }

        // 密码强度校验
        if (!isValidPassword(user.getPassword())) {
            throw new ServiceException("密码必须包含字母和数字，长度8-20位");
        }

        // 自动生成用户名
        if (StringUtils.isBlank(user.getUsername())) {
            if (StringUtils.isNotBlank(user.getPhone())) {
                // user.setUsername(generatePhoneUsername(user.getPhone()));
                user.setUsername(user.getPhone());
            } else {
                // user.setUsername(user.getEmail().split("@")[0]);
                user.setUsername(user.getEmail());
            }
        }

        // 设置注册默认值
        user.setRole(User.Role.COMMON_USER);
        user.setStatus(User.Status.NORMAL);
        user.setIsDeleted(false);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));

        return homeUserMapper.insert(user) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserInfo(User user) {
        // 获取当前登录用户ID
        Integer currentUserId = SecurityUtils.getUserId().intValue();
        if (!user.getId().equals(currentUserId)) {
            throw new ServiceException("只能修改本人信息");
        }

        // 创建更新条件
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId, currentUserId);

        // 允许修改的字段
        if (StringUtils.isNotBlank(user.getUsername())) {
            wrapper.set(User::getUsername, user.getUsername());
        }
        if (user.getSex() != null) {
            wrapper.set(User::getSex, user.getSex());
        }
        if (StringUtils.isNotBlank(user.getAvatar())) {
            wrapper.set(User::getAvatar, user.getAvatar());
        }
        if (StringUtils.isNotBlank(user.getIntroduction())) {
            wrapper.set(User::getIntroduction, user.getIntroduction());
        }

        // 执行更新
        return homeUserMapper.update(null, wrapper) > 0;
    }


    @Override
    public boolean updateUserAvatar(Integer userId, String avatarUrl) {
        return homeUserMapper.update(null,
                new LambdaUpdateWrapper<User>()
                        .eq(User::getId, userId)
                        .set(User::getAvatar, avatarUrl)) > 0;
    }

    private boolean isValidPassword(String password) {
        log.info("password: {}", password);
        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$";
        return password.matches(pattern);
    }

    private String generatePhoneUsername(String phone) {
        if (phone.length() != 11) return "用户" + phone;
        // return "用户" + phone.substring(0, 3) + "****" + phone.substring(7);
        return phone;
    }
}
