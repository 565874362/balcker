package com.baihua.core.modules.basic.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.core.modules.basic.entity.BasDictionaryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 字典
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@Mapper
public interface BasDictionaryDao extends BaseMapper<BasDictionaryEntity> {
	
}
