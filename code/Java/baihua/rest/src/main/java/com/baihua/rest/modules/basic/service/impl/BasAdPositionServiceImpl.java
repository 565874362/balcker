package com.baihua.rest.modules.basic.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.Query;
import com.baihua.core.modules.basic.dao.BasAdPositionDao;
import com.baihua.core.modules.basic.entity.BasAdPositionEntity;
import com.baihua.rest.modules.basic.service.BasAdPositionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("basAdPositionService")
public class BasAdPositionServiceImpl extends ServiceImpl<BasAdPositionDao, BasAdPositionEntity> implements BasAdPositionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasAdPositionEntity> page = this.page(
                new Query<BasAdPositionEntity>(params).getPage(),
                new QueryWrapper<BasAdPositionEntity>()
        );

        return new PageUtils(page);
    }

}