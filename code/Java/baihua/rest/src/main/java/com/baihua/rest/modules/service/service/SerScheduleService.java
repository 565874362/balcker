package com.baihua.rest.modules.service.service;

import java.util.Map;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.modules.service.entity.SerScheduleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 出诊时间表
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:18
 */
public interface SerScheduleService extends IService<SerScheduleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

