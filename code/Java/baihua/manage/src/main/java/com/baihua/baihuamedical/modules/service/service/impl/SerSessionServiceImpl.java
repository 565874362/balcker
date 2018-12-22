package com.baihua.baihuamedical.modules.service.service.impl;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.service.dao.SerSessionDao;
import com.baihua.baihuamedical.modules.service.entity.SerSessionEntity;
import com.baihua.baihuamedical.modules.service.service.SerSessionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


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
