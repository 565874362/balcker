package com.baihua.core.modules.service.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

import com.baihua.core.modules.service.entity.SerRegistrationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 挂号
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:18
 */
@Mapper
public interface SerRegistrationDao extends BaseMapper<SerRegistrationEntity> {

    Long registListTotal(@Param("boid") Long boid, @Param("gender") Integer gender, @Param("status") Integer status, @Param("startDate") String startDate, @Param("endDate") String endDate);

    IPage<Map<String,Object>> registList(@Param("page") IPage<SerRegistrationEntity> page, @Param("boid") Long boid, @Param("gender") Integer gender, @Param("status") Integer status, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
}
