package com.ruoyi.project.home.service;

public interface IAuthService {
    String login(String username, String password);

    void logout(String username);
}
