package com.baihua.rest.modules.service.service;

import java.util.Map;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.modules.service.entity.SerInquiryMatchEntity;
import com.baomidou.mybatisplus.extension.service.IService;

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

