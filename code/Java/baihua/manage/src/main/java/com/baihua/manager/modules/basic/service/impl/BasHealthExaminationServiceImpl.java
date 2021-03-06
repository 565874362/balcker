package com.baihua.manager.modules.basic.service.impl;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.Query;
import com.baihua.core.modules.basic.dao.BasHealthExaminationDao;
import com.baihua.core.modules.basic.entity.BasHealthExaminationEntity;
import com.baihua.manager.modules.basic.service.BasHealthExaminationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("basHealthExaminationService")
public class BasHealthExaminationServiceImpl extends ServiceImpl<BasHealthExaminationDao, BasHealthExaminationEntity> implements BasHealthExaminationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasHealthExaminationEntity> page = this.page(
                new Query<BasHealthExaminationEntity>(params).getPage(),
                new QueryWrapper<BasHealthExaminationEntity>()
        );

        return new PageUtils(page);
    }

}
