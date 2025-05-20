package com.ruoyi.project.home.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.common.domain.Category;
import com.ruoyi.project.common.mapper.CategoryMapper;
import com.ruoyi.project.home.service.IHomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeCategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements IHomeCategoryService {

}
