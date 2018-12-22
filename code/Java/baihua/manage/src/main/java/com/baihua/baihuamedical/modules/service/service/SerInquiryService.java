package com.baihua.baihuamedical.modules.service.service;

import com.baihua.baihuamedical.common.utils.PageUtils;
import com.baihua.baihuamedical.modules.service.entity.SerInquiryEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 问诊
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
public interface SerInquiryService extends IService<SerInquiryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage<SerInquiryEntity> selectDocterIndexPage(Page<SerInquiryEntity> page, Long doctorId);

    void commit(SerInquiryEntity input);

    IPage<Map<String,Object>> queryInquiry(Page<SerInquiryEntity> page, long officeid, long patientid,String hospitalNname,  String startDate, String endDate);
}

