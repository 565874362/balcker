package com.baihua.baihuamedical.modules.service.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.service.dao.SerInquiryDao;
import com.baihua.baihuamedical.modules.service.entity.SerInquiryEntity;
import com.baihua.baihuamedical.modules.service.service.SerInquiryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("serInquiryService")
public class SerInquiryServiceImpl extends ServiceImpl<SerInquiryDao, SerInquiryEntity> implements SerInquiryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SerInquiryEntity> page = this.page(
                new Query<SerInquiryEntity>(params).getPage(),
                new QueryWrapper<SerInquiryEntity>()
        );

        return new PageUtils(page);
    }

}
