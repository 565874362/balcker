package com.baihua.baihuamedical.modules.user.service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.sys.entity.SysCaptchaEntity;
import com.baihua.baihuamedical.modules.user.entity.UsAccountEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 账号
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 10:17:17
 */
public interface UsAccountService extends IService<UsAccountEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(SysCaptchaEntity captchaEntity);

    void checkCaptcha(SysCaptchaEntity captchaEntity);

    UsAccountEntity generatorPatient(String phone);

    String generatorToken(Long id);
}

