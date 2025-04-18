package com.ruoyi.project.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.admin.domain.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 物品类别Mapper接口
 *
 * @author sinvon
 * @date 2025-04-19
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
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
     * 删除物品类别
     *
     * @param id 物品类别主键
     * @return 结果
     */
    public int deleteCategoryById(Integer id);

    /**
     * 批量删除物品类别
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCategoryByIds(Integer[] ids);
}
