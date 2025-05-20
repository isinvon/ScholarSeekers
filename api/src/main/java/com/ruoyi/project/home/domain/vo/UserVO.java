package com.ruoyi.project.home.domain.vo;

import com.ruoyi.project.common.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户信息VO")
public class UserVO extends User {
}