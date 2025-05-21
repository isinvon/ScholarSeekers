package com.ruoyi.project.home.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.common.domain.LostItem;
import com.ruoyi.project.common.service.ILostItemService;
import com.ruoyi.project.home.other.utils.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/home/lostItem")
public class HomeLostItemController extends BaseController {
    @Autowired
    private ILostItemService lostItemService;
    @Autowired
    private UserContextHolder userContextHolder;

    @PostMapping("/create")
    public AjaxResult add(@RequestBody LostItem lostItem) {
        // 补充文件路径处理
        if (StringUtils.isNotEmpty(lostItem.getImageUrl())) {
            lostItem.setImageUrl(lostItem.getImageUrl().replace(RuoYiConfig.getProfile(), ""));
        }
        // 从安全上下文获取用户ID
        Integer currentUserId = userContextHolder.getCurrentUserId();
        lostItem.setPublisherId(currentUserId);
        lostItem.setStatus(0); // 默认未认领状态
        lostItem.setIsDeleted(false);
        return lostItemService.save(lostItem) ?
                AjaxResult.success("创建成功") :
                AjaxResult.error("创建失败");
    }

    /**
     * 某个用户的发布
     *
     * @return
     */
    @PostMapping("/lostItemListForCurrentUser")
    public AjaxResult lostItemForCurrentUser() {
        Integer currentUserId = userContextHolder.getCurrentUserId();
        List<LostItem> list = lostItemService.lambdaQuery()
                .eq(LostItem::getPublisherId, currentUserId)
                .eq(LostItem::getIsDeleted, false)
                .orderByDesc(LostItem::getCreateTime)
                .list();
        return AjaxResult.success(list);
    }

    /**
     * 编辑失物
     *
     * @param lostItem
     * @return
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody LostItem lostItem) {
        if (lostItem == null) {
            return AjaxResult.error("编辑信息不能为空");
        }
        return lostItemService.updateById(lostItem) ?
                AjaxResult.success("编辑成功") :
                AjaxResult.error("编辑失败");
    }
}
