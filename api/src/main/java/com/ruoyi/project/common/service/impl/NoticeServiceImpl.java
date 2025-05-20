package com.ruoyi.project.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.common.domain.Notice;
import com.ruoyi.project.common.mapper.NoticeMapper;
import com.ruoyi.project.common.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通知Service业务层处理
 *
 * @author sinvon
 * @date 2025-04-19
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 查询通知
     *
     * @param id 通知主键
     * @return 通知
     */
    @Override
    public Notice selectNoticeById(Long id) {
        return noticeMapper.selectNoticeById(id);
    }

    /**
     * 查询通知列表
     *
     * @param notice 通知
     * @return 通知
     */
    @Override
    public List<Notice> selectNoticeList(Notice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增通知
     *
     * @param notice 通知
     * @return 结果
     */
    @Override
    public int insertNotice(Notice notice) {
        notice.setCreateTime(DateUtils.getNowDate());
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改通知
     *
     * @param notice 通知
     * @return 结果
     */
    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 批量删除通知
     *
     * @param ids 需要删除的通知主键
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] ids) {
        return noticeMapper.deleteNoticeByIds(ids);
    }

    /**
     * 删除通知信息
     *
     * @param id 通知主键
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long id) {
        return noticeMapper.deleteNoticeById(id);
    }
}
