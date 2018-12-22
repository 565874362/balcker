package com.baihua.baihuamedical.modules.service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baihua.baihuamedical.modules.service.entity.SerInquiryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.validation.Constraint;
import java.util.List;
import java.util.Map;

/**
 * 问诊
 * 
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@Mapper
public interface SerInquiryDao extends BaseMapper<SerInquiryEntity> {

	long selectDoctorIndexTotal(@Param("doctorId") Long doctorId);

	IPage<SerInquiryEntity> selectDoctorIndexPage(Page<SerInquiryEntity> page, @Param("doctorId") Long doctorId);

	long queryInquiryTotal(@Param("officeid") long officeid, @Param("patientid") long patientid,@Param("hospitalNname") String hospitalNname, @Param("startDate") String startDate, @Param("endDate") String endDate);

	IPage<Map<String,Object>> queryInquiry(Page<SerInquiryEntity> page,@Param("officeid") long officeid, @Param("patientid") long patientid,@Param("hospitalNname") String hospitalNname, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
