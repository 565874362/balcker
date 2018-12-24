package com.baihua.core.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.core.modules.sys.entity.SysRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 角色管理
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2016年9月18日 上午9:33:33
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
