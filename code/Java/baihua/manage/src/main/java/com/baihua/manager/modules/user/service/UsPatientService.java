package com.baihua.manager.modules.user.service;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.modules.user.entity.UsPatientEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 患者
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
public interface UsPatientService extends IService<UsPatientEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

