package com.baihua.baihuamedical.modules.basic.service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.basic.entity.BasHealthExaminationEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


/**
 * 检查
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
public interface BasHealthExaminationService extends IService<BasHealthExaminationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

