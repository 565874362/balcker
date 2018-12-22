package com.baihua.baihuamedical.modules.service.service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.service.entity.SerInquiryMatchEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 问诊匹配
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:18
 */
public interface SerInquiryMatchService extends IService<SerInquiryMatchEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

