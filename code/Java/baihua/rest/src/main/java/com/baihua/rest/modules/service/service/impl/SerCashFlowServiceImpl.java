package com.baihua.rest.modules.service.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.Query;
import com.baihua.core.modules.service.dao.SerCashFlowDao;
import com.baihua.core.modules.service.entity.SerCashFlowEntity;
import com.baihua.rest.modules.service.service.SerCashFlowService;
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
