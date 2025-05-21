package com.ruoyi.project.common.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.common.domain.Category;

/**
 * 物品类别Service接口
 * 
 * @author sinvon
 * @date 2025-04-19
 */
public interface ICategoryService extends IService<Category>
{
    /**
     * 查询物品类别
     * 
     * @param id 物品类别主键
     * @return 物品类别
     */
    public Category selectCategoryById(Integer id);

    /**
     * 查询物品类别列表
     * 
     * @param category 物品类别
     * @return 物品类别集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增物品类别
     * 
     * @param category 物品类别
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改物品类别
     * 
     * @param category 物品类别
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 批量删除物品类别
     * 
     * @param ids 需要删除的物品类别主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(Integer[] ids);

    /**
     * 删除物品类别信息
     * 
     * @param id 物品类别主键
     * @return 结果
     */
    public int deleteCategoryById(Integer id);

    /**
     * 获取分类树形结构
     */
    List<Category> getCategoryTree();
}
