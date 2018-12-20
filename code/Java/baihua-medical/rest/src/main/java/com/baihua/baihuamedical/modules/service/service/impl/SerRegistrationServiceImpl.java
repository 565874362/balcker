package com.baihua.baihuamedical.modules.service.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baihua.baihuamedical.common.enums.Constants;
import com.baihua.baihuamedical.common.exception.BaihuaException;
import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.common.utils.Query;
import com.baihua.baihuamedical.modules.service.dao.SerRegistrationDao;
import com.baihua.baihuamedical.modules.service.dao.SerScheduleDao;
import com.baihua.baihuamedical.modules.service.entity.SerRegistrationEntity;
import com.baihua.baihuamedical.modules.service.entity.SerScheduleEntity;
import com.baihua.baihuamedical.modules.service.service.SerRegistrationService;
import com.baihua.baihuamedical.modules.user.dao.UsDoctorDao;
import com.baihua.baihuamedical.modules.user.entity.UsDoctorEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("serRegistrationService")
public class SerRegistrationServiceImpl extends ServiceImpl<SerRegistrationDao, SerRegistrationEntity> implements SerRegistrationService {

	@Autowired
    private SerScheduleDao serScheduleDao;

	@Autowired
	private UsDoctorDao doctorDao;

	@Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SerRegistrationEntity> page = this.page(
                new Query<SerRegistrationEntity>(params).getPage(),
                new QueryWrapper<SerRegistrationEntity>()
        );
        return new PageUtils(page);
    }

	@Override
	@Transactional
	public synchronized void registrate(SerRegistrationEntity serRegistrationEntity) {
		UsDoctorEntity doctorEntity = doctorDao.selectOne(new QueryWrapper<UsDoctorEntity>().lambda()
				.select(UsDoctorEntity::getId,UsDoctorEntity::getStatus,UsDoctorEntity::getAppointmentNum, UsDoctorEntity::getRegistrationFee)
				.eq(UsDoctorEntity::getId, serRegistrationEntity.getDoctorId()));
		if(doctorEntity == null || doctorEntity.getStatus().intValue() == Constants.DoctorStatus.uncheck.getCode()){
			throw new BaihuaException("非法操作");
		}
		serRegistrationEntity.setFee(doctorEntity.getRegistrationFee());
		SerScheduleEntity serScheduleEntity = serScheduleDao.selectById(serRegistrationEntity.getScheduleId());
		if(serRegistrationEntity.getTimePart().intValue() == Constants.ScheduleType.morning.getCode()){
			if(serScheduleEntity.getMorningRemainNum() == 0) {
				throw new BaihuaException("挂号人已满");
			}
			serScheduleEntity.setMorningRemainNum(serScheduleEntity.getMorningRemainNum() - 1);
		}else{
			if(serScheduleEntity.getAfternoonRemainNum() == 0){
				throw new BaihuaException("挂号人已满");
			}
			serScheduleEntity.setAfternoonRemainNum(serScheduleEntity.getAfternoonRemainNum() - 1);
		}
		serScheduleDao.updateById(serScheduleEntity);
		doctorEntity.setAppointmentNum(doctorEntity.getAppointmentNum() + 1);
		doctorDao.updateById(doctorEntity);
		serRegistrationEntity.setStatus(Constants.RegistrationStatus.unpaid.getCode());
		baseMapper.insert(serRegistrationEntity);
	}
}