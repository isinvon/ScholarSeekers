package com.ruoyi.project.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.admin.mapper.NewsMapper;
import com.ruoyi.project.admin.domain.News;
import com.ruoyi.project.admin.service.INewsService;

/**
 * 公告新闻Service业务层处理
 * 
 * @author sinvon
 * @date 2025-04-19
 */
@Service
public class NewsServiceImpl implements INewsService 
{
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 查询公告新闻
     * 
     * @param id 公告新闻主键
     * @return 公告新闻
     */
    @Override
    public News selectNewsById(Integer id)
    {
        return newsMapper.selectNewsById(id);
    }

    /**
     * 查询公告新闻列表
     * 
     * @param news 公告新闻
     * @return 公告新闻
     */
    @Override
    public List<News> selectNewsList(News news)
    {
        return newsMapper.selectNewsList(news);
    }

    /**
     * 新增公告新闻
     * 
     * @param news 公告新闻
     * @return 结果
     */
    @Override
    public int insertNews(News news)
    {
        news.setCreateTime(DateUtils.getNowDate());
        return newsMapper.insertNews(news);
    }

    /**
     * 修改公告新闻
     * 
     * @param news 公告新闻
     * @return 结果
     */
    @Override
    public int updateNews(News news)
    {
        news.setUpdateTime(DateUtils.getNowDate());
        return newsMapper.updateNews(news);
    }

    /**
     * 批量删除公告新闻
     * 
     * @param ids 需要删除的公告新闻主键
     * @return 结果
     */
    @Override
    public int deleteNewsByIds(Integer[] ids)
    {
        return newsMapper.deleteNewsByIds(ids);
    }

    /**
     * 删除公告新闻信息
     * 
     * @param id 公告新闻主键
     * @return 结果
     */
    @Override
    public int deleteNewsById(Integer id)
    {
        return newsMapper.deleteNewsById(id);
    }
}
