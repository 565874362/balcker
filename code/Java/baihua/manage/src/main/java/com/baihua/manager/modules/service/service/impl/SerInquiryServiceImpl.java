package com.baihua.manager.modules.service.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.Query;
import com.baihua.core.modules.service.dao.SerInquiryDao;
import com.baihua.core.modules.user.dao.UsDoctorDao;
import com.baihua.core.modules.service.entity.SerInquiryEntity;
import com.baihua.core.modules.user.dao.UsDoctorDao;
import com.baihua.manager.modules.service.service.SerInquiryMatchService;
import com.baihua.manager.modules.service.service.SerInquiryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
	public IPage<Map<String, Object>> queryInquiry(Page<SerInquiryEntity> page,long officeid, long patientid,String hospitalNname ,String startDate, String endDate) {
		page.setOptimizeCountSql(false);
		page.setTotal(inquiryDao.queryInquiryTotal(officeid,patientid,hospitalNname,startDate,endDate));
		return inquiryDao.queryInquiry(page,officeid,patientid,hospitalNname,startDate,endDate);
	}

	@Override
	public IPage<Map<String, Object>> inqueryList(Page<SerInquiryEntity> page,Integer gender, String startDate, String endDate) {
		page.setOptimizeCountSql(false);
		page.setTotal(inquiryDao.inqueryListTotal(gender,startDate,endDate));
		return inquiryDao.inqueryList(page,gender,startDate,endDate);
	}

	@Override
	public void delById(Long id) {
		inquiryDao.delById(id);
	}
}
