package com.baihua.baihuamedical.modules.basic.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.basic.dao.BasDictionaryDao;
import com.baihua.baihuamedical.modules.basic.entity.BasDictionaryEntity;
import com.baihua.baihuamedical.modules.basic.service.BasDictionaryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("basDictionaryService")
public class BasDictionaryServiceImpl extends ServiceImpl<BasDictionaryDao, BasDictionaryEntity> implements BasDictionaryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasDictionaryEntity> page = this.page(
                new Query<BasDictionaryEntity>(params).getPage(),
                new QueryWrapper<BasDictionaryEntity>()
        );

        return new PageUtils(page);
    }

}
