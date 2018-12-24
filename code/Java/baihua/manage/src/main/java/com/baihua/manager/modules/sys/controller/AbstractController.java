package com.baihua.manager.modules.sys.controller;

import org.apache.shiro.SecurityUtils;

import com.baihua.core.modules.sys.entity.SysUserEntity;

import lombok.extern.slf4j.Slf4j;


/**
 * Controller公共组件
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月9日 下午9:42:26
 */
@Slf4j
public abstract class AbstractController {

	protected SysUserEntity getUser() {
		return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
