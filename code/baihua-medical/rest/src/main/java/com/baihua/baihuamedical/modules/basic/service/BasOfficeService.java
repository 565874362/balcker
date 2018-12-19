package com.baihua.baihuamedical.modules.basic.service;

import java.util.Map;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.basic.entity.BasOfficeEntity;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 科室
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
public interface BasOfficeService extends IService<BasOfficeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

