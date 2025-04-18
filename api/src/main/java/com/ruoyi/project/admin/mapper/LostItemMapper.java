package com.ruoyi.project.admin.mapper;

import java.util.List;
import com.ruoyi.project.admin.domain.LostItem;

/**
 * 丢失物品Mapper接口
 * 
 * @author sinvon
 * @date 2025-04-19
 */
public interface LostItemMapper 
{
    /**
     * 查询丢失物品
     * 
     * @param id 丢失物品主键
     * @return 丢失物品
     */
    public LostItem selectLostItemById(Integer id);

    /**
     * 查询丢失物品列表
     * 
     * @param lostItem 丢失物品
     * @return 丢失物品集合
     */
    public List<LostItem> selectLostItemList(LostItem lostItem);

    /**
     * 新增丢失物品
     * 
     * @param lostItem 丢失物品
     * @return 结果
     */
    public int insertLostItem(LostItem lostItem);

    /**
     * 修改丢失物品
     * 
     * @param lostItem 丢失物品
     * @return 结果
     */
    public int updateLostItem(LostItem lostItem);

    /**
     * 删除丢失物品
     * 
     * @param id 丢失物品主键
     * @return 结果
     */
    public int deleteLostItemById(Integer id);

    /**
     * 批量删除丢失物品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLostItemByIds(Integer[] ids);
}
