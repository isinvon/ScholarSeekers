package com.ruoyi.project.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.project.admin.domain.SeekArticle;
import com.ruoyi.project.admin.service.ISeekArticleService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 寻物启事Controller
 * 
 * @author sinvon
 * @date 2025-04-19
 */
@RestController
@RequestMapping("/admin/article")
public class SeekArticleController extends BaseController
{
    @Autowired
    private ISeekArticleService seekArticleService;

    /**
     * 查询寻物启事列表
     */
    @PreAuthorize("@ss.hasPermi('admin:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(SeekArticle seekArticle)
    {
        startPage();
        List<SeekArticle> list = seekArticleService.selectSeekArticleList(seekArticle);
        return getDataTable(list);
    }

    /**
     * 导出寻物启事列表
     */
    @PreAuthorize("@ss.hasPermi('admin:article:export')")
    @Log(title = "寻物启事", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SeekArticle seekArticle)
    {
        List<SeekArticle> list = seekArticleService.selectSeekArticleList(seekArticle);
        ExcelUtil<SeekArticle> util = new ExcelUtil<SeekArticle>(SeekArticle.class);
        util.exportExcel(response, list, "寻物启事数据");
    }

    /**
     * 获取寻物启事详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:article:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(seekArticleService.selectSeekArticleById(id));
    }

    /**
     * 新增寻物启事
     */
    @PreAuthorize("@ss.hasPermi('admin:article:add')")
    @Log(title = "寻物启事", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SeekArticle seekArticle)
    {
        return toAjax(seekArticleService.insertSeekArticle(seekArticle));
    }

    /**
     * 修改寻物启事
     */
    @PreAuthorize("@ss.hasPermi('admin:article:edit')")
    @Log(title = "寻物启事", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SeekArticle seekArticle)
    {
        return toAjax(seekArticleService.updateSeekArticle(seekArticle));
    }

    /**
     * 删除寻物启事
     */
    @PreAuthorize("@ss.hasPermi('admin:article:remove')")
    @Log(title = "寻物启事", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(seekArticleService.deleteSeekArticleByIds(ids));
    }
}
