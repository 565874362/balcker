package com.baihua.baihuamedical.modules.service.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.service.dao.SerCashFlowDao;
import com.baihua.baihuamedical.modules.service.entity.SerCashFlowEntity;
import com.baihua.baihuamedical.modules.service.service.SerCashFlowService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service("serCashFlowService")
public class SerCashFlowServiceImpl extends ServiceImpl<SerCashFlowDao, SerCashFlowEntity> implements SerCashFlowService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SerCashFlowEntity> page = this.page(
                new Query<SerCashFlowEntity>(params).getPage(),
                new QueryWrapper<SerCashFlowEntity>()
        );

        return new PageUtils(page);
    }

}
