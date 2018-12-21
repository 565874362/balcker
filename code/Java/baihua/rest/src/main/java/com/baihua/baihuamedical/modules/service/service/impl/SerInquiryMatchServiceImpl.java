package com.baihua.baihuamedical.modules.service.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.service.dao.SerInquiryMatchDao;
import com.baihua.baihuamedical.modules.service.entity.SerInquiryMatchEntity;
import com.baihua.baihuamedical.modules.service.service.SerInquiryMatchService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("serInquiryMatchService")
public class SerInquiryMatchServiceImpl extends ServiceImpl<SerInquiryMatchDao, SerInquiryMatchEntity> implements SerInquiryMatchService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SerInquiryMatchEntity> page = this.page(
                new Query<SerInquiryMatchEntity>(params).getPage(),
                new QueryWrapper<SerInquiryMatchEntity>()
        );

        return new PageUtils(page);
    }

}
