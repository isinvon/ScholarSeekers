package com.ruoyi.project.admin.service;

import java.util.List;
import com.ruoyi.project.admin.domain.SeekArticle;

/**
 * 寻物启事Service接口
 * 
 * @author sinvon
 * @date 2025-04-19
 */
public interface ISeekArticleService 
{
    /**
     * 查询寻物启事
     * 
     * @param id 寻物启事主键
     * @return 寻物启事
     */
    public SeekArticle selectSeekArticleById(Integer id);

    /**
     * 查询寻物启事列表
     * 
     * @param seekArticle 寻物启事
     * @return 寻物启事集合
     */
    public List<SeekArticle> selectSeekArticleList(SeekArticle seekArticle);

    /**
     * 新增寻物启事
     * 
     * @param seekArticle 寻物启事
     * @return 结果
     */
    public int insertSeekArticle(SeekArticle seekArticle);

    /**
     * 修改寻物启事
     * 
     * @param seekArticle 寻物启事
     * @return 结果
     */
    public int updateSeekArticle(SeekArticle seekArticle);

    /**
     * 批量删除寻物启事
     * 
     * @param ids 需要删除的寻物启事主键集合
     * @return 结果
     */
    public int deleteSeekArticleByIds(Integer[] ids);

    /**
     * 删除寻物启事信息
     * 
     * @param id 寻物启事主键
     * @return 结果
     */
    public int deleteSeekArticleById(Integer id);
}
