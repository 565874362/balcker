package com.baihua.baihuamedical.modules.service.service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.service.entity.SerCommentEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 评论
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
public interface SerCommentService extends IService<SerCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

