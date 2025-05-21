package com.ruoyi.project.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.common.domain.LostItem;
import com.ruoyi.project.common.mapper.LostItemMapper;
import com.ruoyi.project.common.service.ILostItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 丢失物品Service业务层处理
 *
 * @author sinvon
 * @date 2025-04-19
 */
@Service
public class LostItemServiceImpl extends ServiceImpl<LostItemMapper, LostItem> implements ILostItemService {
    @Autowired
    private LostItemMapper lostItemMapper;

    /**
     * 查询丢失物品
     *
     * @param id 丢失物品主键
     * @return 丢失物品
     */
    @Override
    public LostItem selectLostItemById(Integer id) {
        return lostItemMapper.selectLostItemById(id);
    }

    /**
     * 查询丢失物品列表
     *
     * @param lostItem 丢失物品
     * @return 丢失物品
     */
    @Override
    public List<LostItem> selectLostItemList(LostItem lostItem) {
        lostItem.setIsDeleted(false); // 只查询未删除的数据
        return lostItemMapper.selectLostItemList(lostItem);
    }

    /**
     * 新增丢失物品
     *
     * @param lostItem 丢失物品
     * @return 结果
     */
    @Override
    public int insertLostItem(LostItem lostItem) {
        lostItem.setCreateTime(DateUtils.getNowDate());
        return lostItemMapper.insertLostItem(lostItem);
    }

    /**
     * 修改丢失物品
     *
     * @param lostItem 丢失物品
     * @return 结果
     */
    @Override
    public int updateLostItem(LostItem lostItem) {
        lostItem.setUpdateTime(DateUtils.getNowDate());
        return lostItemMapper.updateLostItem(lostItem);
    }

    /**
     * 批量删除丢失物品
     *
     * @param ids 需要删除的丢失物品主键
     * @return 结果
     */
    @Override
    public int deleteLostItemByIds(Integer[] ids) {
        return lostItemMapper.deleteLostItemByIds(ids);
    }

    /**
     * 删除丢失物品信息
     *
     * @param id 丢失物品主键
     * @return 结果
     */
    @Override
    public int deleteLostItemById(Integer id) {
        return lostItemMapper.deleteLostItemById(id);
    }
}
