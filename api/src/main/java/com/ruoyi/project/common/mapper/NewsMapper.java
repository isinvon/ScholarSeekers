package com.ruoyi.project.common.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.common.domain.News;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公告新闻Mapper接口
 *
 * @author sinvon
 * @date 2025-04-19
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {
    /**
     * 查询公告新闻
     *
     * @param id 公告新闻主键
     * @return 公告新闻
     */
    public News selectNewsById(Integer id);

    /**
     * 查询公告新闻列表
     *
     * @param news 公告新闻
     * @return 公告新闻集合
     */
    public List<News> selectNewsList(News news);

    /**
     * 新增公告新闻
     *
     * @param news 公告新闻
     * @return 结果
     */
    public int insertNews(News news);

    /**
     * 修改公告新闻
     *
     * @param news 公告新闻
     * @return 结果
     */
    public int updateNews(News news);

    /**
     * 删除公告新闻
     *
     * @param id 公告新闻主键
     * @return 结果
     */
    public int deleteNewsById(Integer id);

    /**
     * 批量删除公告新闻
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsByIds(Integer[] ids);
}
