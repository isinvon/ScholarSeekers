package com.ruoyi.project.home.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户头像更新参数")
public class UserAvatarDTO {
    @ApiModelProperty(value = "用户ID", required = true)
    private Integer userId;

    @ApiModelProperty(value = "头像地址", required = true)
    private String avatarUrl;
}
