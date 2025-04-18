package com.ruoyi.project.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.admin.domain.Comment;
import com.ruoyi.project.admin.mapper.CommentMapper;
import com.ruoyi.project.admin.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论Service业务层处理
 *
 * @author sinvon
 * @date 2025-04-19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 查询评论
     *
     * @param id 评论主键
     * @return 评论
     */
    @Override
    public Comment selectCommentById(Integer id) {
        return commentMapper.selectCommentById(id);
    }

    /**
     * 查询评论列表
     *
     * @param comment 评论
     * @return 评论
     */
    @Override
    public List<Comment> selectCommentList(Comment comment) {
        return commentMapper.selectCommentList(comment);
    }

    /**
     * 新增评论
     *
     * @param comment 评论
     * @return 结果
     */
    @Override
    public int insertComment(Comment comment) {
        comment.setCreateTime(DateUtils.getNowDate());
        return commentMapper.insertComment(comment);
    }

    /**
     * 修改评论
     *
     * @param comment 评论
     * @return 结果
     */
    @Override
    public int updateComment(Comment comment) {
        comment.setUpdateTime(DateUtils.getNowDate());
        return commentMapper.updateComment(comment);
    }

    /**
     * 批量删除评论
     *
     * @param ids 需要删除的评论主键
     * @return 结果
     */
    @Override
    public int deleteCommentByIds(Integer[] ids) {
        return commentMapper.deleteCommentByIds(ids);
    }

    /**
     * 删除评论信息
     *
     * @param id 评论主键
     * @return 结果
     */
    @Override
    public int deleteCommentById(Integer id) {
        return commentMapper.deleteCommentById(id);
    }
}
