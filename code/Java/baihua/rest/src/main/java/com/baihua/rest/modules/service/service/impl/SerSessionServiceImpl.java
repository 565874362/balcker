package com.baihua.rest.modules.service.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.Query;
import com.baihua.core.modules.service.dao.SerSessionDao;
import com.baihua.core.modules.service.entity.SerSessionEntity;
import com.baihua.rest.modules.service.service.SerSessionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("serSessionService")
public class SerSessionServiceImpl extends ServiceImpl<SerSessionDao, SerSessionEntity> implements SerSessionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SerSessionEntity> page = this.page(
                new Query<SerSessionEntity>(params).getPage(),
                new QueryWrapper<SerSessionEntity>()
        );

        return new PageUtils(page);
    }

}
