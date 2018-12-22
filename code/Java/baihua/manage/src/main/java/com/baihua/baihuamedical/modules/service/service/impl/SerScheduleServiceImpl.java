package com.baihua.baihuamedical.modules.service.service.impl;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.service.dao.SerScheduleDao;
import com.baihua.baihuamedical.modules.service.entity.SerScheduleEntity;
import com.baihua.baihuamedical.modules.service.service.SerScheduleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("serScheduleService")
public class SerScheduleServiceImpl extends ServiceImpl<SerScheduleDao, SerScheduleEntity> implements SerScheduleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SerScheduleEntity> page = this.page(
                new Query<SerScheduleEntity>(params).getPage(),
                new QueryWrapper<SerScheduleEntity>()
        );

        return new PageUtils(page);
    }

}
