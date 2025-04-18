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
import com.ruoyi.project.admin.domain.LostItem;
import com.ruoyi.project.admin.service.ILostItemService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 丢失物品Controller
 * 
 * @author sinvon
 * @date 2025-04-19
 */
@RestController
@RequestMapping("/admin/item")
public class LostItemController extends BaseController
{
    @Autowired
    private ILostItemService lostItemService;

    /**
     * 查询丢失物品列表
     */
    @PreAuthorize("@ss.hasPermi('admin:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(LostItem lostItem)
    {
        startPage();
        List<LostItem> list = lostItemService.selectLostItemList(lostItem);
        return getDataTable(list);
    }

    /**
     * 导出丢失物品列表
     */
    @PreAuthorize("@ss.hasPermi('admin:item:export')")
    @Log(title = "丢失物品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LostItem lostItem)
    {
        List<LostItem> list = lostItemService.selectLostItemList(lostItem);
        ExcelUtil<LostItem> util = new ExcelUtil<LostItem>(LostItem.class);
        util.exportExcel(response, list, "丢失物品数据");
    }

    /**
     * 获取丢失物品详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(lostItemService.selectLostItemById(id));
    }

    /**
     * 新增丢失物品
     */
    @PreAuthorize("@ss.hasPermi('admin:item:add')")
    @Log(title = "丢失物品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LostItem lostItem)
    {
        return toAjax(lostItemService.insertLostItem(lostItem));
    }

    /**
     * 修改丢失物品
     */
    @PreAuthorize("@ss.hasPermi('admin:item:edit')")
    @Log(title = "丢失物品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LostItem lostItem)
    {
        return toAjax(lostItemService.updateLostItem(lostItem));
    }

    /**
     * 删除丢失物品
     */
    @PreAuthorize("@ss.hasPermi('admin:item:remove')")
    @Log(title = "丢失物品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(lostItemService.deleteLostItemByIds(ids));
    }
}
