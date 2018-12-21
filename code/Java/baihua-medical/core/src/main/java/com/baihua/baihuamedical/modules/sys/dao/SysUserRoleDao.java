package com.baihua.baihuamedical.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baihua.baihuamedical.modules.sys.entity.SysUserRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 用户与角色对应关系
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2016年9月18日 上午9:34:46
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);


	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
}
