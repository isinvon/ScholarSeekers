package com.ruoyi.project.home.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.common.domain.Notice;
import com.ruoyi.project.home.mapper.HomeNoticeMapper;
import com.ruoyi.project.home.service.IHomeNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeNoticeServiceImpl extends ServiceImpl<HomeNoticeMapper, Notice> implements IHomeNoticeService {
}
