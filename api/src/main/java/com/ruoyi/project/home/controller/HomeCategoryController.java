package com.ruoyi.project.home.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.domain.Category;
import com.ruoyi.project.common.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/category")
public class HomeCategoryController extends BaseController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/list")
    public TableDataInfo list(@RequestBody Category category) {
        startPage();
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    /**
     * 获取分类树形数据
     */
    @PostMapping("/tree")
    public AjaxResult getTree() {
        return success(categoryService.getCategoryTree());
    }

    /**
     * 新增快捷查询接口
     *
     * @param keyword
     * @return
     */
    @GetMapping("/quickList")
    public AjaxResult quickList(@RequestParam(required = false) String keyword) {
        Category query = new Category();
        query.setName(keyword);
        List<Category> list = categoryService.selectCategoryList(query);
        return success(list);
    }
}
