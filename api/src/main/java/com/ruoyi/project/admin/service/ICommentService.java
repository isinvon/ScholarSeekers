package com.ruoyi.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.admin.domain.Comment;

import java.util.List;

/**
 * 评论Service接口
 *
 * @author sinvon
 * @date 2025-04-19
 */
public interface ICommentService extends IService<Comment> {
    /**
     * 查询评论
     *
     * @param id 评论主键
     * @return 评论
     */
    public Comment selectCommentById(Integer id);

    /**
     * 查询评论列表
     *
     * @param comment 评论
     * @return 评论集合
     */
    public List<Comment> selectCommentList(Comment comment);

    /**
     * 新增评论
     *
     * @param comment 评论
     * @return 结果
     */
    public int insertComment(Comment comment);

    /**
     * 修改评论
     *
     * @param comment 评论
     * @return 结果
     */
    public int updateComment(Comment comment);

    /**
     * 批量删除评论
     *
     * @param ids 需要删除的评论主键集合
     * @return 结果
     */
    public int deleteCommentByIds(Integer[] ids);

    /**
     * 删除评论信息
     *
     * @param id 评论主键
     * @return 结果
     */
    public int deleteCommentById(Integer id);
}
