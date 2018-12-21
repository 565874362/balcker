package com.baihua.baihuamedical.modules.sys.service;

import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.modules.sys.entity.SysUserTokenEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户Token
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:07
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
