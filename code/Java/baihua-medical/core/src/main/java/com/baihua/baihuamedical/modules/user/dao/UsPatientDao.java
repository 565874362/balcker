package com.baihua.baihuamedical.modules.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.baihuamedical.modules.user.entity.UsPatientEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 患者
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@Mapper
public interface UsPatientDao extends BaseMapper<UsPatientEntity> {
	
}
