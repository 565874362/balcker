package com.baihua.baihuamedical.modules.basic.service.impl;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.basic.dao.BasAdDao;
import com.baihua.baihuamedical.modules.basic.entity.BasAdEntity;
import com.baihua.baihuamedical.modules.basic.service.BasAdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("basAdService")
public class BasAdServiceImpl extends ServiceImpl<BasAdDao, BasAdEntity> implements BasAdService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasAdEntity> page = this.page(
                new Query<BasAdEntity>(params).getPage(),
                new QueryWrapper<BasAdEntity>()
        );

        return new PageUtils(page);
    }

}
