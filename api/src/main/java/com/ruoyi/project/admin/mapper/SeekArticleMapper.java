package com.ruoyi.project.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.admin.domain.Category;
import com.ruoyi.project.admin.domain.SeekArticle;
import org.apache.ibatis.annotations.Mapper;

/**
 * 寻物启事Mapper接口
 *
 * @author sinvon
 * @date 2025-04-19
 */
@Mapper
public interface SeekArticleMapper extends BaseMapper<SeekArticle> {
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
     * 删除寻物启事
     *
     * @param id 寻物启事主键
     * @return 结果
     */
    public int deleteSeekArticleById(Integer id);

    /**
     * 批量删除寻物启事
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSeekArticleByIds(Integer[] ids);
}
