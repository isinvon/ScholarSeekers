package com.ruoyi.project.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.common.domain.Feedback;

import java.util.List;

/**
 * 帮助与反馈Service接口
 *
 * @author sinvon
 * @date 2025-04-19
 */
public interface IFeedbackService extends IService<Feedback> {
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
     * 批量删除帮助与反馈
     *
     * @param ids 需要删除的帮助与反馈主键集合
     * @return 结果
     */
    public int deleteFeedbackByIds(Integer[] ids);

    /**
     * 删除帮助与反馈信息
     *
     * @param id 帮助与反馈主键
     * @return 结果
     */
    public int deleteFeedbackById(Integer id);
}
