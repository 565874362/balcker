package com.baihua.rest.modules.user.service.impl;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baihua.core.common.enums.Constants;
import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.Query;
import com.baihua.core.modules.service.dao.SerInquiryMatchDao;
import com.baihua.core.modules.service.entity.SerAdeptEntity;
import com.baihua.rest.modules.service.service.SerAdeptService;
import com.baihua.core.modules.user.dao.UsAccountDao;
import com.baihua.core.modules.user.dao.UsDoctorDao;
import com.baihua.core.modules.user.entity.UsAccountEntity;
import com.baihua.core.modules.user.entity.UsDoctorEntity;
import com.baihua.rest.modules.user.service.UsDoctorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("usDoctorService")
public class UsDoctorServiceImpl extends ServiceImpl<UsDoctorDao, UsDoctorEntity> implements UsDoctorService {

	@Autowired
	private SerAdeptService adeptService;

	@Autowired
	private UsAccountDao accountDao;

	@Autowired
	private SerInquiryMatchDao inquiryMatchDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UsDoctorEntity> page = this.page(
                new Query<UsDoctorEntity>(params).getPage(),
                new QueryWrapper<UsDoctorEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	@Transactional
	public void register(String account,UsDoctorEntity doctorEntity, List<SerAdeptEntity> serAdeptEntity) {
		doctorEntity.setStatus(Constants.DoctorStatus.uncheck.getCode());
		doctorEntity.setAppointmentNum(0);
		save(doctorEntity);
		ListIterator<SerAdeptEntity> serAdeptEntityListIterator = serAdeptEntity.listIterator();
		while (serAdeptEntityListIterator.hasNext()) {
			SerAdeptEntity each = serAdeptEntityListIterator.next();
			each.setDocId(doctorEntity.getId());
			each.setOrdered(serAdeptEntityListIterator.nextIndex());
		}
		adeptService.saveBatch(serAdeptEntity);

		UsAccountEntity accountEntity = new UsAccountEntity();
		accountEntity.setAccount(account);
		accountEntity.setSId(doctorEntity.getId());
		accountEntity.setSName(doctorEntity.getName());
		accountEntity.setStatus(Constants.AccountStatus.waitactive.getCode());
		accountEntity.setType(Constants.AccountType.doctor.getCode());
		accountDao.insert(accountEntity);
	}

	@Override
	@Transactional
	public void update(UsDoctorEntity doctorEntity, List<SerAdeptEntity> serAdeptEntity) {
		doctorEntity.setStatus(Constants.DoctorStatus.uncheck.getCode());
		updateById(doctorEntity);

		adeptService.remove(new QueryWrapper<SerAdeptEntity>().lambda().eq(SerAdeptEntity::getDocId, doctorEntity.getId()));
		ListIterator<SerAdeptEntity> serAdeptEntityListIterator = serAdeptEntity.listIterator();
		while (serAdeptEntityListIterator.hasNext()) {
			SerAdeptEntity each = serAdeptEntityListIterator.next();
			each.setDocId(doctorEntity.getId());
			each.setOrdered(serAdeptEntityListIterator.nextIndex());
		}
		adeptService.saveBatch(serAdeptEntity);

		UsAccountEntity accountEntity = new UsAccountEntity();
		accountEntity.setStatus(Constants.AccountStatus.waitactive.getCode());
		accountDao.update(accountEntity,new QueryWrapper<UsAccountEntity>().lambda()
				.eq(UsAccountEntity::getSId,doctorEntity.getId())
				.eq(UsAccountEntity::getType,Constants.AccountType.doctor));
	}
}
