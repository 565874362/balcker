package com.baihua.baihuamedical.modules.basic.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.basic.dao.BasDiseaseCategoryDao;
import com.baihua.baihuamedical.modules.basic.entity.BasDiseaseCategoryEntity;
import com.baihua.baihuamedical.modules.basic.service.BasDiseaseCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("basDiseaseCategoryService")
public class BasDiseaseCategoryServiceImpl extends ServiceImpl<BasDiseaseCategoryDao, BasDiseaseCategoryEntity> implements BasDiseaseCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasDiseaseCategoryEntity> page = this.page(
                new Query<BasDiseaseCategoryEntity>(params).getPage(),
                new QueryWrapper<BasDiseaseCategoryEntity>()
        );

        return new PageUtils(page);
    }

}
