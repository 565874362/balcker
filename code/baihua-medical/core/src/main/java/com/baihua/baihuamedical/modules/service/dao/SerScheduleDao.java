package com.baihua.baihuamedical.modules.service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.baihuamedical.modules.service.entity.SerScheduleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 出诊时间表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:18
 */
@Mapper
public interface SerScheduleDao extends BaseMapper<SerScheduleEntity> {
	
}
