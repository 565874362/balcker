package com.baihua.core.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.core.modules.sys.entity.SysUserTokenEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 系统用户Token
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
