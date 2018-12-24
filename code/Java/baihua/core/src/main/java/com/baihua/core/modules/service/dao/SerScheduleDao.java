package com.baihua.core.modules.service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.core.modules.service.entity.SerScheduleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 出诊时间表
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:18
 */
@Mapper
public interface SerScheduleDao extends BaseMapper<SerScheduleEntity> {
	
}
