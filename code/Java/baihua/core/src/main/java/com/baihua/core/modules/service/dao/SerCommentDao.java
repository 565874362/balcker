package com.baihua.core.modules.service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.core.modules.service.entity.SerCommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 评论
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@Mapper
public interface SerCommentDao extends BaseMapper<SerCommentEntity> {
	
}
