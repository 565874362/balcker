package com.baihua.baihuamedical.modules.service.service;

import java.util.Map;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.service.entity.SerInquiryEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 问诊
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
public interface SerInquiryService extends IService<SerInquiryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

