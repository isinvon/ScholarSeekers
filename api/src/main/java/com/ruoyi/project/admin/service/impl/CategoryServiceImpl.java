package com.ruoyi.project.admin.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.admin.mapper.CategoryMapper;
import com.ruoyi.project.admin.domain.Category;
import com.ruoyi.project.admin.service.ICategoryService;

/**
 * 物品类别Service业务层处理
 * 
 * @author sinvon
 * @date 2025-04-19
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService
{
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询物品类别
     * 
     * @param id 物品类别主键
     * @return 物品类别
     */
    @Override
    public Category selectCategoryById(Integer id)
    {
        return categoryMapper.selectCategoryById(id);
    }

    /**
     * 查询物品类别列表
     * 
     * @param category 物品类别
     * @return 物品类别
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增物品类别
     * 
     * @param category 物品类别
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        category.setCreateTime(DateUtils.getNowDate());
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改物品类别
     * 
     * @param category 物品类别
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        category.setUpdateTime(DateUtils.getNowDate());
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除物品类别
     * 
     * @param ids 需要删除的物品类别主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(Integer[] ids)
    {
        return categoryMapper.deleteCategoryByIds(ids);
    }

    /**
     * 删除物品类别信息
     * 
     * @param id 物品类别主键
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Integer id)
    {
        return categoryMapper.deleteCategoryById(id);
    }
}
