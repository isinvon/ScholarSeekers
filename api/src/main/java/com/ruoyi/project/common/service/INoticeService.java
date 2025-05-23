package com.ruoyi.project.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.common.domain.Notice;

import java.util.List;

/**
 * 通知Service接口
 *
 * @author sinvon
 * @date 2025-04-19
 */
public interface INoticeService extends IService<Notice> {
    /**
     * 查询通知
     *
     * @param id 通知主键
     * @return 通知
     */
    public Notice selectNoticeById(Long id);

    /**
     * 查询通知列表
     *
     * @param notice 通知
     * @return 通知集合
     */
    public List<Notice> selectNoticeList(Notice notice);

    /**
     * 新增通知
     *
     * @param notice 通知
     * @return 结果
     */
    public int insertNotice(Notice notice);

    /**
     * 修改通知
     *
     * @param notice 通知
     * @return 结果
     */
    public int updateNotice(Notice notice);

    /**
     * 批量删除通知
     *
     * @param ids 需要删除的通知主键集合
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] ids);

    /**
     * 删除通知信息
     *
     * @param id 通知主键
     * @return 结果
     */
    public int deleteNoticeById(Long id);
}
