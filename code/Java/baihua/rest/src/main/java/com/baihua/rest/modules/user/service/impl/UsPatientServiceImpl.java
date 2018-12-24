package com.baihua.rest.modules.user.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.Query;
import com.baihua.core.modules.user.dao.UsPatientDao;
import com.baihua.core.modules.user.entity.UsPatientEntity;
import com.baihua.rest.modules.user.service.UsPatientService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("usPatientService")
public class UsPatientServiceImpl extends ServiceImpl<UsPatientDao, UsPatientEntity> implements UsPatientService {

    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsPatientEntity> page = this.page(
                new Query<UsPatientEntity>(params).getPage(),
                new QueryWrapper<UsPatientEntity>()
        );

        return new PageUtils(page);
    }
}
