package com.baihua.baihuamedical.modules.service.service;

import java.util.Map;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.service.entity.SerCommentEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 评论
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
public interface SerCommentService extends IService<SerCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

