package com.baihua.rest.modules.basic.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.Query;
import com.baihua.core.modules.basic.dao.BasHospitalDao;
import com.baihua.core.modules.basic.entity.BasHospitalEntity;
import com.baihua.rest.modules.basic.service.BasHospitalService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("basHospitalService")
public class BasHospitalServiceImpl extends ServiceImpl<BasHospitalDao, BasHospitalEntity> implements BasHospitalService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasHospitalEntity> page = this.page(
                new Query<BasHospitalEntity>(params).getPage(),
                new QueryWrapper<BasHospitalEntity>()
        );

        return new PageUtils(page);
    }

}
