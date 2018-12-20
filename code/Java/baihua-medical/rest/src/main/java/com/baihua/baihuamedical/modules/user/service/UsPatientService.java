package com.baihua.baihuamedical.modules.user.service;

import java.util.Map;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.user.entity.UsPatientEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 患者
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
public interface UsPatientService extends IService<UsPatientEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

