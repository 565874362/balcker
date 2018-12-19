package com.baihua.baihuamedical.modules.basic.service;

import java.util.Map;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.basic.entity.BasDictionaryEntity;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 字典
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
public interface BasDictionaryService extends IService<BasDictionaryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

