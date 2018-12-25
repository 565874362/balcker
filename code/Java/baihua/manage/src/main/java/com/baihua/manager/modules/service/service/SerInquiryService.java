package com.baihua.manager.modules.service.service;

import java.util.Map;

import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.modules.service.entity.SerInquiryEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
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

    IPage<Map<String,Object>> queryInquiry(Page<SerInquiryEntity> page, long officeid, long patientid,String hospitalNname,  String startDate, String endDate);

    IPage<Map<String,Object>> inqueryList(Page<SerInquiryEntity> page,Integer gender, String startDate, String endDate);

    void delById(Long id);
}

