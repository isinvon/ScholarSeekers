package com.ruoyi.project.home.domain.query;

import com.ruoyi.project.common.domain.LostItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class LostItemQuery extends LostItem {
    @NotBlank(message = "物品标题不能为空")
    private String title;

    @NotBlank(message = "详细描述不能为空")
    private String description;

    @NotNull(message = "分类不能为空")
    private Integer categoryId;

    @NotBlank(message = "丢失地点不能为空")
    private String lostLocation;

    @NotNull(message = "丢失时间不能为空")
    private Date lostTime;

    @Pattern(regexp = "^1[3-9]\\d{9}$|^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$",
            message = "联系方式格式不正确")
    private String contactInfo;
}

