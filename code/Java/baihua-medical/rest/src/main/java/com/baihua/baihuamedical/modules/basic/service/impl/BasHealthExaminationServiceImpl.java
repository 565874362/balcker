package com.baihua.baihuamedical.modules.basic.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.basic.dao.BasHealthExaminationDao;
import com.baihua.baihuamedical.modules.basic.entity.BasHealthExaminationEntity;
import com.baihua.baihuamedical.modules.basic.service.BasHealthExaminationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



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
