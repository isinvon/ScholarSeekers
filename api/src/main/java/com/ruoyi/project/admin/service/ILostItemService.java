package com.ruoyi.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.admin.domain.LostItem;

import java.util.List;

/**
 * 丢失物品Service接口
 *
 * @author sinvon
 * @date 2025-04-19
 */
public interface ILostItemService extends IService<LostItem> {
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
     * 批量删除丢失物品
     *
     * @param ids 需要删除的丢失物品主键集合
     * @return 结果
     */
    public int deleteLostItemByIds(Integer[] ids);

    /**
     * 删除丢失物品信息
     *
     * @param id 丢失物品主键
     * @return 结果
     */
    public int deleteLostItemById(Integer id);
}
