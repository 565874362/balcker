package com.baihua.rest.modules.service.service;

import java.util.Map;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.modules.service.entity.SerAdeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 擅长领域
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
public interface SerAdeptService extends IService<SerAdeptEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

