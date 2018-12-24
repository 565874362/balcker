package com.baihua.rest.modules.basic.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.Query;
import com.baihua.core.modules.basic.dao.BasDictionaryDao;
import com.baihua.core.modules.basic.entity.BasDictionaryEntity;
import com.baihua.rest.modules.basic.service.BasDictionaryService;
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
