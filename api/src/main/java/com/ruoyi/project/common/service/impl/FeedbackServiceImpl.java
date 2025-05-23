package com.ruoyi.project.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.common.domain.Feedback;
import com.ruoyi.project.common.mapper.FeedbackMapper;
import com.ruoyi.project.common.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 帮助与反馈Service业务层处理
 *
 * @author sinvon
 * @date 2025-04-19
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 查询帮助与反馈
     *
     * @param id 帮助与反馈主键
     * @return 帮助与反馈
     */
    @Override
    public Feedback selectFeedbackById(Integer id) {
        return feedbackMapper.selectFeedbackById(id);
    }

    /**
     * 查询帮助与反馈列表
     *
     * @param feedback 帮助与反馈
     * @return 帮助与反馈
     */
    @Override
    public List<Feedback> selectFeedbackList(Feedback feedback) {
        return feedbackMapper.selectFeedbackList(feedback);
    }

    /**
     * 新增帮助与反馈
     *
     * @param feedback 帮助与反馈
     * @return 结果
     */
    @Override
    public int insertFeedback(Feedback feedback) {
        feedback.setCreateTime(DateUtils.getNowDate());
        return feedbackMapper.insertFeedback(feedback);
    }

    /**
     * 修改帮助与反馈
     *
     * @param feedback 帮助与反馈
     * @return 结果
     */
    @Override
    public int updateFeedback(Feedback feedback) {
        feedback.setUpdateTime(DateUtils.getNowDate());
        return feedbackMapper.updateFeedback(feedback);
    }

    /**
     * 批量删除帮助与反馈
     *
     * @param ids 需要删除的帮助与反馈主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackByIds(Integer[] ids) {
        return feedbackMapper.deleteFeedbackByIds(ids);
    }

    /**
     * 删除帮助与反馈信息
     *
     * @param id 帮助与反馈主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackById(Integer id) {
        return feedbackMapper.deleteFeedbackById(id);
    }
}
