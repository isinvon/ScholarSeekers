package com.ruoyi.project.home.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.common.domain.User;
import com.ruoyi.project.home.domain.vo.UserVO;

import java.util.List;

public interface IHomeUserService extends IService<User> {

    boolean updateUserStatus(Integer userId, User.Status status);

    boolean updateAvatar(Integer userId, String avatarUrl);

    User selectUserByUsername(String username);

    List<User> selectUserList(User user);
    boolean createUser(User user);
    boolean updateUser(User user);
    boolean deleteUsers(List<Integer> userIds);

    boolean registerUser(User user);
    boolean updateUserInfo(User user);
    User getCurrentUserInfo();

    boolean updateUserAvatar(Integer userId, String avatarUrl);
}
