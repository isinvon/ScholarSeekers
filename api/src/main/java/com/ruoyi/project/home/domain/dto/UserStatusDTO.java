package com.ruoyi.project.home.domain.dto;

import com.ruoyi.project.common.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户状态更新参数")
public class UserStatusDTO {
    @ApiModelProperty(value = "用户ID", required = true)
    private Integer userId;

    @ApiModelProperty(value = "用户状态", required = true)
    private User.Status status;
}