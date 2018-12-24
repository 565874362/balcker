package com.baihua.core.modules.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.core.modules.user.entity.UsDoctorEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 医生
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@Mapper
public interface UsDoctorDao extends BaseMapper<UsDoctorEntity> {
	
}
