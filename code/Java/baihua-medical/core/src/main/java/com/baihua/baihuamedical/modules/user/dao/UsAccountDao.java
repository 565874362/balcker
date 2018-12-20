package com.baihua.baihuamedical.modules.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.baihuamedical.modules.user.entity.UsAccountEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 账号
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
@Mapper
public interface UsAccountDao extends BaseMapper<UsAccountEntity> {
	
}
