package com.baihua.baihuamedical.modules.service.service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.service.entity.SerSessionEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 会话历史
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:18
 */
public interface SerSessionService extends IService<SerSessionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

