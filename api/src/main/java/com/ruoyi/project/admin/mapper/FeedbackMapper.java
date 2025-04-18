package com.ruoyi.project.admin.mapper;

import java.util.List;
import com.ruoyi.project.admin.domain.Feedback;

/**
 * 帮助与反馈Mapper接口
 * 
 * @author sinvon
 * @date 2025-04-19
 */
public interface FeedbackMapper 
{
    /**
     * 查询帮助与反馈
     * 
     * @param id 帮助与反馈主键
     * @return 帮助与反馈
     */
    public Feedback selectFeedbackById(Integer id);

    /**
     * 查询帮助与反馈列表
     * 
     * @param feedback 帮助与反馈
     * @return 帮助与反馈集合
     */
    public List<Feedback> selectFeedbackList(Feedback feedback);

    /**
     * 新增帮助与反馈
     * 
     * @param feedback 帮助与反馈
     * @return 结果
     */
    public int insertFeedback(Feedback feedback);

    /**
     * 修改帮助与反馈
     * 
     * @param feedback 帮助与反馈
     * @return 结果
     */
    public int updateFeedback(Feedback feedback);

    /**
     * 删除帮助与反馈
     * 
     * @param id 帮助与反馈主键
     * @return 结果
     */
    public int deleteFeedbackById(Integer id);

    /**
     * 批量删除帮助与反馈
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFeedbackByIds(Integer[] ids);
}
