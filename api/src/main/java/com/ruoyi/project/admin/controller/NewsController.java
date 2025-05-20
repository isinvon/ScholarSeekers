package com.ruoyi.project.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.common.domain.News;
import com.ruoyi.project.common.service.INewsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 公告新闻Controller
 * 
 * @author sinvon
 * @date 2025-04-19
 */
@RestController
@RequestMapping("/admin/news")
public class NewsController extends BaseController
{
    @Autowired
    private INewsService newsService;

    /**
     * 查询公告新闻列表
     */
    @PreAuthorize("@ss.hasPermi('admin:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(News news)
    {
        startPage();
        List<News> list = newsService.selectNewsList(news);
        return getDataTable(list);
    }

    /**
     * 导出公告新闻列表
     */
    @PreAuthorize("@ss.hasPermi('admin:news:export')")
    @Log(title = "公告新闻", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, News news)
    {
        List<News> list = newsService.selectNewsList(news);
        ExcelUtil<News> util = new ExcelUtil<News>(News.class);
        util.exportExcel(response, list, "公告新闻数据");
    }

    /**
     * 获取公告新闻详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:news:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(newsService.selectNewsById(id));
    }

    /**
     * 新增公告新闻
     */
    @PreAuthorize("@ss.hasPermi('admin:news:add')")
    @Log(title = "公告新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody News news)
    {
        // 获取当前用户id
        int userId = SecurityUtils.getUserId().intValue();
        news.setPublisherId(userId); // 设置发布者id
        news.setIsDeleted(false);
        news.setType(News.Type.COMMON);
        return toAjax(newsService.insertNews(news));
    }

    /**
     * 修改公告新闻
     */
    @PreAuthorize("@ss.hasPermi('admin:news:edit')")
    @Log(title = "公告新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody News news)
    {
        return toAjax(newsService.updateNews(news));
    }

    /**
     * 删除公告新闻
     */
    @PreAuthorize("@ss.hasPermi('admin:news:remove')")
    @Log(title = "公告新闻", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(newsService.deleteNewsByIds(ids));
    }
}
