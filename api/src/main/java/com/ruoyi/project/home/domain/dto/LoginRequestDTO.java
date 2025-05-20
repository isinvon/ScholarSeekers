package com.ruoyi.project.home.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("登录请求参数")
public class LoginRequestDTO {
    private String username;
    private String password;
}
