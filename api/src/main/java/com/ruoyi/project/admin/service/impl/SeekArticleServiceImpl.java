package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.admin.domain.SeekArticle;
import com.ruoyi.project.admin.mapper.SeekArticleMapper;
import com.ruoyi.project.admin.service.ISeekArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 寻物启事Service业务层处理
 *
 * @author sinvon
 * @date 2025-04-19
 */
@Service
public class SeekArticleServiceImpl extends ServiceImpl<SeekArticleMapper, SeekArticle> implements ISeekArticleService {
    @Autowired
    private SeekArticleMapper seekArticleMapper;

    /**
     * 查询寻物启事
     *
     * @param id 寻物启事主键
     * @return 寻物启事
     */
    @Override
    public SeekArticle selectSeekArticleById(Integer id) {
        return seekArticleMapper.selectSeekArticleById(id);
    }

    /**
     * 查询寻物启事列表
     *
     * @param seekArticle 寻物启事
     * @return 寻物启事
     */
    @Override
    public List<SeekArticle> selectSeekArticleList(SeekArticle seekArticle) {
        return seekArticleMapper.selectSeekArticleList(seekArticle);
    }

    /**
     * 新增寻物启事
     *
     * @param seekArticle 寻物启事
     * @return 结果
     */
    @Override
    public int insertSeekArticle(SeekArticle seekArticle) {
        seekArticle.setCreateTime(DateUtils.getNowDate());
        return seekArticleMapper.insertSeekArticle(seekArticle);
    }

    /**
     * 修改寻物启事
     *
     * @param seekArticle 寻物启事
     * @return 结果
     */
    @Override
    public int updateSeekArticle(SeekArticle seekArticle) {
        seekArticle.setUpdateTime(DateUtils.getNowDate());
        return seekArticleMapper.updateSeekArticle(seekArticle);
    }

    /**
     * 批量删除寻物启事
     *
     * @param ids 需要删除的寻物启事主键
     * @return 结果
     */
    @Override
    public int deleteSeekArticleByIds(Integer[] ids) {
        return seekArticleMapper.deleteSeekArticleByIds(ids);
    }

    /**
     * 删除寻物启事信息
     *
     * @param id 寻物启事主键
     * @return 结果
     */
    @Override
    public int deleteSeekArticleById(Integer id) {
        return seekArticleMapper.deleteSeekArticleById(id);
    }
}
