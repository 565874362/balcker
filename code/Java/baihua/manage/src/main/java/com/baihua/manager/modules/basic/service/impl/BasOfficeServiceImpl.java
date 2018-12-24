package com.baihua.manager.modules.basic.service.impl;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.Query;
import com.baihua.core.modules.basic.dao.BasOfficeDao;
import com.baihua.core.modules.basic.entity.BasOfficeEntity;
import com.baihua.manager.modules.basic.service.BasOfficeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("basOfficeService")
public class BasOfficeServiceImpl extends ServiceImpl<BasOfficeDao, BasOfficeEntity> implements BasOfficeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasOfficeEntity> page = this.page(
                new Query<BasOfficeEntity>(params).getPage(),
                new QueryWrapper<BasOfficeEntity>()
        );

        return new PageUtils(page);
    }

}
