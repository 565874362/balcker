package com.baihua.rest.modules.user.service;

import java.util.List;
import java.util.Map;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.modules.service.entity.SerAdeptEntity;
import com.baihua.core.modules.user.entity.UsDoctorEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 医生
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
public interface UsDoctorService extends IService<UsDoctorEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void register(String account,UsDoctorEntity doctorEntity, List<SerAdeptEntity> serAdeptEntity);

    void update(UsDoctorEntity doctorEntity, List<SerAdeptEntity> serAdeptEntity);

}

