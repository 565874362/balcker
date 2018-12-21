package com.baihua.baihuamedical.modules.basic.service;

import java.util.Map;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.basic.entity.BasAdEntity;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 广告
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
public interface BasAdService extends IService<BasAdEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

