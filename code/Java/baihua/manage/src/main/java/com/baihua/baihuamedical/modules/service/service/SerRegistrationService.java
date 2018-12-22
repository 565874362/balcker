package com.baihua.baihuamedical.modules.service.service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.service.entity.SerRegistrationEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 挂号
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:18
 */
public interface SerRegistrationService extends IService<SerRegistrationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void registrate(SerRegistrationEntity serRegistrationEntity);

}

