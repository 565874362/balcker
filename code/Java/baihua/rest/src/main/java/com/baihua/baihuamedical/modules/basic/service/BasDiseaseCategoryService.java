package com.baihua.baihuamedical.modules.basic.service;

import java.util.Map;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.basic.entity.BasDiseaseCategoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 疾病分类
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
public interface BasDiseaseCategoryService extends IService<BasDiseaseCategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

