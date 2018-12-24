package com.baihua.rest.modules.basic.service;

import java.util.Map;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.modules.basic.entity.BasOfficeEntity;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 科室
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
public interface BasOfficeService extends IService<BasOfficeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

