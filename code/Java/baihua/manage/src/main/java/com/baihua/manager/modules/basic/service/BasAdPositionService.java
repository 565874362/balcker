package com.baihua.manager.modules.basic.service;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.modules.basic.entity.BasAdPositionEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;


/**
 * 广告位
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
public interface BasAdPositionService extends IService<BasAdPositionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

