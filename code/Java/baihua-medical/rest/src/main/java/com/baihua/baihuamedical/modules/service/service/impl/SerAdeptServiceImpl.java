package com.baihua.baihuamedical.modules.service.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.service.dao.SerAdeptDao;
import com.baihua.baihuamedical.modules.service.entity.SerAdeptEntity;
import com.baihua.baihuamedical.modules.service.service.SerAdeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("serAdeptService")
public class SerAdeptServiceImpl extends ServiceImpl<SerAdeptDao, SerAdeptEntity> implements SerAdeptService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SerAdeptEntity> page = this.page(
                new Query<SerAdeptEntity>(params).getPage(),
                new QueryWrapper<SerAdeptEntity>()
        );

        return new PageUtils(page);
    }
}
