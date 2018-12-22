package com.baihua.baihuamedical.modules.basic.service.impl;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.basic.dao.BasHospitalDao;
import com.baihua.baihuamedical.modules.basic.entity.BasHospitalEntity;
import com.baihua.baihuamedical.modules.basic.service.BasHospitalService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;


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
