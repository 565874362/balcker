package com.baihua.baihuamedical.modules.service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.baihuamedical.modules.service.entity.SerSessionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 会话历史
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:18
 */
@Mapper
public interface SerSessionDao extends BaseMapper<SerSessionEntity> {
	
}
