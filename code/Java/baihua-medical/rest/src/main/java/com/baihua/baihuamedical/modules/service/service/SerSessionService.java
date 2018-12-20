package com.baihua.baihuamedical.modules.service.service;

import java.util.Map;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.service.entity.SerSessionEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 会话历史
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:18
 */
public interface SerSessionService extends IService<SerSessionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

