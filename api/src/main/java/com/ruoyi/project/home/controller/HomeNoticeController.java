package com.ruoyi.project.home.controller;


import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.home.service.IHomeNoticeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/home/notice")
public class HomeNoticeController {
    @Resource
    private IHomeNoticeService homeNoticeService;

    /**
     * 查询所有公告
     *
     * @return
     */
    @RequestMapping("/list")
    public AjaxResult list() {
        return AjaxResult.success(homeNoticeService.list());
    }
}
