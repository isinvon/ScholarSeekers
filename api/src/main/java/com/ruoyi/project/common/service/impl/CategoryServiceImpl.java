package com.ruoyi.project.common.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.common.mapper.CategoryMapper;
import com.ruoyi.project.common.domain.Category;
import com.ruoyi.project.common.service.ICategoryService;

/**
 * 物品类别Service业务层处理
 *
 * @author sinvon
 * @date 2025-04-19
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询物品类别
     *
     * @param id 物品类别主键
     * @return 物品类别
     */
    @Override
    public Category selectCategoryById(Integer id) {
        return categoryMapper.selectCategoryById(id);
    }

    /**
     * 查询物品类别列表
     *
     * @param category 物品类别
     * @return 物品类别
     */
    @Override
    public List<Category> selectCategoryList(Category category) {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增物品类别
     *
     * @param category 物品类别
     * @return 结果
     */
    @Override
    public int insertCategory(Category category) {
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
    public int updateCategory(Category category) {
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
    public int deleteCategoryByIds(Integer[] ids) {
        return categoryMapper.deleteCategoryByIds(ids);
    }

    /**
     * 删除物品类别信息
     *
     * @param id 物品类别主键
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Integer id) {
        return categoryMapper.deleteCategoryById(id);
    }

    @Override
    public List<Category> getCategoryTree() {
        List<Category> allCategories = categoryMapper.selectCategoryList(new Category());
        return buildTree(allCategories);
    }

    private List<Category> buildTree(List<Category> list) {
        Map<Integer, Category> map = new LinkedHashMap<>();

        // 第一遍处理：初始化所有节点并建立映射
        for (Category item : list) {
            item.setChildren(new ArrayList<>());
            map.put(item.getId(), item);
        }

        // 第二遍处理：构建树形结构
        List<Category> roots = new ArrayList<>();
        for (Category item : list) {
            Integer parentId = item.getParentId();
            if (parentId == null || parentId == 0) {
                roots.add(item);
            } else {
                Category parent = map.get(parentId);
                if (parent != null) {
                    parent.getChildren().add(item);
                    item.setParentName(parent.getName());
                }
            }
        }
        return roots;
    }
}
