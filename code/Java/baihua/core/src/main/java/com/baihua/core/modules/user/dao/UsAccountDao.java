package com.baihua.core.modules.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.core.modules.user.entity.UsAccountEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 账号
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@Mapper
public interface UsAccountDao extends BaseMapper<UsAccountEntity> {
	
}
