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
import com.ruoyi.project.common.domain.Claim;
import com.ruoyi.project.common.service.IClaimService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 认领记录Controller
 * 
 * @author sinvon
 * @date 2025-04-19
 */
@RestController
@RequestMapping("/admin/claim")
public class ClaimController extends BaseController
{
    @Autowired
    private IClaimService claimService;

    /**
     * 查询认领记录列表
     */
    @PreAuthorize("@ss.hasPermi('admin:claim:list')")
    @GetMapping("/list")
    public TableDataInfo list(Claim claim)
    {
        startPage();
        List<Claim> list = claimService.selectClaimList(claim);
        return getDataTable(list);
    }

    /**
     * 导出认领记录列表
     */
    @PreAuthorize("@ss.hasPermi('admin:claim:export')")
    @Log(title = "认领记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Claim claim)
    {
        List<Claim> list = claimService.selectClaimList(claim);
        ExcelUtil<Claim> util = new ExcelUtil<Claim>(Claim.class);
        util.exportExcel(response, list, "认领记录数据");
    }

    /**
     * 获取认领记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('admin:claim:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(claimService.selectClaimById(id));
    }

    /**
     * 新增认领记录
     */
    @PreAuthorize("@ss.hasPermi('admin:claim:add')")
    @Log(title = "认领记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Claim claim)
    {
        claim.setIsDeleted(false);
        return toAjax(claimService.insertClaim(claim));
    }

    /**
     * 修改认领记录
     */
    @PreAuthorize("@ss.hasPermi('admin:claim:edit')")
    @Log(title = "认领记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Claim claim)
    {
        return toAjax(claimService.updateClaim(claim));
    }

    /**
     * 删除认领记录
     */
    @PreAuthorize("@ss.hasPermi('admin:claim:remove')")
    @Log(title = "认领记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(claimService.deleteClaimByIds(ids));
    }
}
