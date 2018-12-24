package com.baihua.rest.modules.service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baihua.core.common.enums.Constants;
import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.Query;
import com.baihua.core.modules.basic.service.IDoctorMatchService;
import com.baihua.core.modules.service.dao.SerInquiryDao;
import com.baihua.core.modules.service.entity.SerInquiryEntity;
import com.baihua.core.modules.service.entity.SerInquiryMatchEntity;
import com.baihua.rest.modules.service.service.SerInquiryMatchService;
import com.baihua.rest.modules.service.service.SerInquiryService;
import com.baihua.core.modules.user.dao.UsDoctorDao;
import com.baihua.core.modules.user.entity.UsDoctorEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("serInquiryService")
public class SerInquiryServiceImpl extends ServiceImpl<SerInquiryDao, SerInquiryEntity> implements SerInquiryService {

	@Autowired
	private SerInquiryDao inquiryDao;

	@Autowired
	private UsDoctorDao usDoctorDao;

	@Autowired
	private SerInquiryMatchService serInquiryMatchService;

	@Autowired
	private IDoctorMatchService keywordService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SerInquiryEntity> page = this.page(
                new Query<SerInquiryEntity>(params).getPage(),
                new QueryWrapper<SerInquiryEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public IPage<SerInquiryEntity> selectDocterIndexPage(Page<SerInquiryEntity> page, Long doctorId) {
		page.setOptimizeCountSql(false);
		page.setTotal(inquiryDao.selectDoctorIndexTotal(doctorId));
		return inquiryDao.selectDoctorIndexPage(page, doctorId);
	}

	@Override
	@Transactional
	public List<UsDoctorEntity> commit(SerInquiryEntity input) {
		List<UsDoctorEntity> doctorEntities = new ArrayList<>();
		inquiryDao.insert(input);
		List<IDoctorMatchService.MatchDoctor> keyword = keywordService.findKeyword(input.getCharacterDescribe());
		if(keyword.isEmpty()){
			return doctorEntities;
		}

		List<SerInquiryMatchEntity> serInquiryMatchEntities = new ArrayList<>();
		for (IDoctorMatchService.MatchDoctor matchDoctor : keyword) {
			doctorEntities.add(matchDoctor.getDoctorEntity());
			SerInquiryMatchEntity serInquiryMatchEntity = new SerInquiryMatchEntity();
			serInquiryMatchEntity.setPatId(input.getPatientId());
			serInquiryMatchEntity.setDocId(matchDoctor.getDoctorEntity().getId());
			serInquiryMatchEntity.setInquiryId(input.getId());
			serInquiryMatchEntity.setMatchNum(matchDoctor.getMatchNum());
			serInquiryMatchEntities.add(serInquiryMatchEntity);
		}
		serInquiryMatchService.saveBatch(serInquiryMatchEntities);

		return doctorEntities.subList(0,keyword.size() > 3 ? 3 : keyword.size());
	}

	@Override
	@Transactional
	public boolean aquire(long inquiryId, long doctorId) {
		SerInquiryEntity updater = new SerInquiryEntity();
		updater.setDoctorId(doctorId);
		updater.setStatus(Constants.InquiryStatus.checked.getCode());

		LambdaUpdateWrapper<SerInquiryEntity> wrapper = new UpdateWrapper<SerInquiryEntity>().lambda()
				.eq(SerInquiryEntity::getId, inquiryId)
				.eq(SerInquiryEntity::getStatus, Constants.InquiryStatus.waitcheck.getCode());
		int result = baseMapper.update(updater, wrapper);
		if(result > 0){
			serInquiryMatchService.remove(new QueryWrapper<SerInquiryMatchEntity>().lambda().eq(SerInquiryMatchEntity::getInquiryId, inquiryId));
		}
    	return result == 1;
	}
}
