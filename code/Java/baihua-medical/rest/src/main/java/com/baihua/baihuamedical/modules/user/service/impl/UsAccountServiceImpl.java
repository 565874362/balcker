package com.baihua.baihuamedical.modules.user.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baihua.baihuamedical.common.configs.ConfigProperties;
import com.baihua.baihuamedical.common.enums.Constants;
import com.baihua.baihuamedical.common.exception.ParameterException;
import com.baihua.baihuamedical.common.utils.DateUtils;
import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.sys.dao.SysCaptchaDao;
import com.baihua.baihuamedical.modules.sys.entity.SysCaptchaEntity;
import com.baihua.baihuamedical.modules.user.dao.UsAccountDao;
import com.baihua.baihuamedical.modules.user.dao.UsDoctorDao;
import com.baihua.baihuamedical.modules.user.dao.UsPatientDao;
import com.baihua.baihuamedical.modules.user.dao.UsTokenDao;
import com.baihua.baihuamedical.modules.user.entity.UsAccountEntity;
import com.baihua.baihuamedical.modules.user.entity.UsPatientEntity;
import com.baihua.baihuamedical.modules.user.entity.UsTokenEntity;
import com.baihua.baihuamedical.modules.user.service.UsAccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("usAccountService")
public class UsAccountServiceImpl extends ServiceImpl<UsAccountDao, UsAccountEntity> implements UsAccountService {

	@Autowired
	private UsPatientDao patientDao;

	@Autowired
	private UsDoctorDao doctorDao;

	@Autowired
	private UsTokenDao userTokenDao;

	@Autowired
	private SysCaptchaDao sysCaptchaDao;

	@Autowired
	private ConfigProperties configProperties;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsAccountEntity> page = this.page(
                new Query<UsAccountEntity>(params).getPage(),
                new QueryWrapper<UsAccountEntity>()
        );
        return new PageUtils(page);
    }

	/**
	 * 保存验证码
	 *
	 * @param captchaEntity
	 */
	@Override
	public void save(SysCaptchaEntity captchaEntity) {
		sysCaptchaDao.insert(captchaEntity);
	}

	@Override
	public void checkCaptcha(SysCaptchaEntity captchaEntity) {
		SysCaptchaEntity sysCaptchaEntity = sysCaptchaDao.selectById(captchaEntity.getUuid());
		if(sysCaptchaEntity == null || sysCaptchaEntity.getExpireTime().before(new Date())){
			throw new ParameterException("验证码已失效");
		}
		if(!sysCaptchaEntity.getCode().equalsIgnoreCase(captchaEntity.getCode())){
			throw new ParameterException("验证码错误");
		}
		sysCaptchaDao.deleteById(captchaEntity.getUuid());
	}

	/**
	 * 增加病人
	 *
	 * @param phone
	 * @return
	 */
	@Override
	@Transactional
	public UsAccountEntity generatorPatient(String phone) {
		UsPatientEntity usPatientEntity = new UsPatientEntity();
		patientDao.insert(usPatientEntity);

		UsAccountEntity accountEntity = new UsAccountEntity();
		accountEntity.setAccount(phone);
		accountEntity.setSId(usPatientEntity.getId());
		accountEntity.setStatus(Constants.AccountStatus.normal.getCode());
		accountEntity.setType(Constants.AccountType.patient.getCode());
		save(accountEntity);
		return accountEntity;
	}

	@Override
	@Transactional
	public String generatorToken(Long id) {
		userTokenDao.delete(new QueryWrapper<UsTokenEntity>().lambda()
				.eq(UsTokenEntity::getAccountId,id));

		UsTokenEntity usTokenEntity = new UsTokenEntity();
		usTokenEntity.setAccountId(id);
		usTokenEntity.setToken(IdWorker.get32UUID());
		usTokenEntity.setExpireTime(DateUtils.addDateDays(new Date(),configProperties.getTokenExpireTime()));
		usTokenEntity.setLastTime(new Date());
		userTokenDao.insert(usTokenEntity);
		return usTokenEntity.getToken();
	}
}
