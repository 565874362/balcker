package com.baihua.baihuamedical.modules.basic.service;

import java.util.Map;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.basic.entity.BasHealthExaminationEntity;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 检查
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
public interface BasHealthExaminationService extends IService<BasHealthExaminationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

