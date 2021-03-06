package com.baihua.core.modules.basic.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.core.modules.basic.entity.BasHospitalEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 医院
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@Mapper
public interface BasHospitalDao extends BaseMapper<BasHospitalEntity> {
	
}
